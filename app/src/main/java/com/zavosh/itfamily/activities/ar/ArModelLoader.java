/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zavosh.itfamily.activities.ar;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.RequiresApi;

import com.google.ar.sceneform.rendering.ModelRenderable;

import java.lang.ref.WeakReference;
import java.util.concurrent.CompletableFuture;

/**
 * Model loader class to avoid memory leaks from the activity. Activity and Fragment controller
 * classes have a lifecycle that is controlled by the UI thread. When a reference to one of these
 * objects is accessed by a background thread it is "leaked". Using that reference to a
 * lifecycle-bound object after Android thinks it has "destroyed" it can produce bugs. It also
 * prevents the Activity or Fragment from being garbage collected, which can leak the memory
 * permanently if the reference is held in the singleton scope.
 *
 * <p>To avoid this, use a non-nested class which is not an activity nor fragment. Hold a weak
 * reference to the activity or fragment and use that when making calls affecting the UI.
 */
@SuppressWarnings({"AndroidApiChecker"})
public class ArModelLoader {
  private static final String TAG = "ModelLoader";
  private final SparseArray<CompletableFuture<ModelRenderable>> futureSet = new SparseArray<>();
  private final WeakReference<ArActivity> owner;

  ArModelLoader(ArActivity owner) {
    this.owner = new WeakReference<>(owner);
  }

  /**
   * Starts loading the model specified. The result of the loading is returned asynchrounously via
   * {@link ArActivity#setRenderable(int, ModelRenderable)} or {@link
   * ArActivity#onException(int, Throwable)}.
   *
   * <p>Multiple models can be loaded at a time by specifying separate ids to differentiate the
   * result on callback.
   *
   * @param id the id for this call to loadModel.
   * @param uri the resource uri of the .sfb to load.
   * @return true if loading was initiated.
   */
  @RequiresApi(api = Build.VERSION_CODES.N)
  boolean loadModel(int id, Uri uri){//int resourceId) { // Change resourceId to recieve Uri.parse("models/frame_upper_right.sfb")
    Log.d("checking loader","checking loader step1");

    try {

    ArActivity activity = owner.get();
    if (activity == null) {
      Log.d(TAG, "Activity is null.  Cannot load model.");
      return false;
    }
    Log.d("checking loader","checking loader step2");

      CompletableFuture<ModelRenderable> future =
              ModelRenderable.builder()
                      .setSource(owner.get(), uri)
                      .build()
                      .thenApply(renderable -> this.setRenderable(id, renderable))
                      .exceptionally(throwable -> this.onException(id, throwable));
      if (future != null) {
        futureSet.put(id, future);
      }

      return future != null;
    }catch (Exception ex){
      Log.d("checking loader","checking loader step3");
    }

    return  false;

  }

  ModelRenderable onException(int id, Throwable throwable) {
    ArActivity activity = owner.get();
    if (activity != null) {
      activity.onException(id, throwable);
    }
    futureSet.remove(id);
    return null;
  }

  ModelRenderable setRenderable(int id, ModelRenderable modelRenderable) {
    ArActivity activity = owner.get();
    if (activity != null) {
      activity.setRenderable(id, modelRenderable);
    }
    futureSet.remove(id);
    return modelRenderable;
  }
}
