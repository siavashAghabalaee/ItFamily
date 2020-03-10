package com.zavosh.itfamily.activities.ar;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImage.TrackingMethod;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.zavosh.itfamily.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArActivity extends AppCompatActivity {

    private boolean cameraPermissionRequested;

    private static final String TAG = AppCompatActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private String arRootPath;

    private ArFragment arFragment;
    private ArModelLoader modelLoader;
    private ImageView fitToScanView;



    private static final int ID_MODEL_FRONTPAGE = 1;
    private static final int ID_MODEL_MIDDLEPAGE = 2;
    private static final int ID_MODEL_BACKPAGE = 3;
    private static final String arFrontPageModelFilename ="itfamili-front-model.sfb";
    private static final String arMiddlePageModelFilename ="itfamili-middle-model.sfb";
    private static final String arBackPageModelFilename ="itfamili-back-model.sfb";



    public HashMap<Integer, ModelRenderable> renderables = new HashMap<>(); //new ArrayList<>(); //ModelRenderable[] renderables; // frontModelRenderable, backModelRenderable, middleModelRenderable;

    public HashMap<Integer, ModelAnimator> animators = new HashMap<>();

    // Augmented image and its associated center pose anchor, keyed by the augmented image in
    // the database.
    private final Map<String, ArSceneAnchorNode> augmentedImageMap = new HashMap<>();

    //private ModelRenderable andyRenderable;
    //private static List<CompletableFuture<ModelRenderable>> renderableChars = new ArrayList<>();
    //private static List<ModelRenderable> renderableChars = new ArrayList<>();

    private TextView textCode;


    /*
    // This section belongs to model renderables - temporary restriction
    private float charScaleFactor=0.3f;

    private int currentRandNum=0, maxNumOfChars=3, currentNumOfSceneChars;

    private int getNextNumber() {
        if(currentRandNum+1>maxNumOfChars-1)
        {
            currentRandNum=0;
            return currentRandNum;
        }
        else{
            currentRandNum++;
            return currentRandNum;
        }

    }*/

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    // CompletableFuture requires api level 24
    protected void onCreate(Bundle savedInstanceState) {

        arRootPath = getFilesDir()  +"/ar/";

        super.onCreate(savedInstanceState);

        if (!ArSystem.checkIsSupportedDeviceOrFinish(ArActivity.this)) {
            return;
        }

        Log.d("checking database","checking database step -1");

        setContentView(R.layout.ar_activity_main);

        Log.d("checking database","checking database step -2");


        textCode = (TextView) findViewById(R.id.textCode);

        Log.d("checking database","checking database step -3");
        /*String availabilityCode = getIntent().getStringExtra("AvailabilityCode");
        if(availabilityCode != null)
            textCode.setText(availabilityCode);*/


        setupARSystem();


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupARSystem() {

        Log.d("checking database","checking database step -4");

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


        // Since we're only looking for ref images
        disablePlaneMechanism();

        Log.d("checking database","checking database step1");


        Log.d("checking database","checking database step2");

        this.renderables.put(ID_MODEL_FRONTPAGE, null);
        this.renderables.put(ID_MODEL_MIDDLEPAGE, null);
        this.renderables.put(ID_MODEL_BACKPAGE, null);

        this.animators.put(ID_MODEL_FRONTPAGE, null);
        this.animators.put(ID_MODEL_MIDDLEPAGE, null);
        this.animators.put(ID_MODEL_BACKPAGE, null);

        Log.d("checking database","checking database step3");
        modelLoader = new ArModelLoader(ArActivity.this);

        modelLoader.loadModel(1, Uri.parse(arRootPath+ "1.sfb"));
        modelLoader.loadModel(2,Uri.parse(arRootPath+ "2.sfb"));
        modelLoader.loadModel(3,Uri.parse(arRootPath+ "3.sfb"));

        Log.d("checking database","checking database step4");

        fitToScanView = findViewById(R.id.image_view_fit_to_scan);

        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);

        Log.d("checking database","checking database step5");


    }

    String currentRenderableName="";

    /**
     * Registered with the Sceneform Scene object, this method is called at the start of each frame.
     *
     * @param frameTime - time since last frame.
     */
    private void onUpdateFrame(FrameTime frameTime) {

        try {

            Frame frame = arFragment.getArSceneView().getArFrame();

            if (frame == null) {
                return;
            }

            // ------------------------------------------------------

            Collection<AugmentedImage> updatedAiCollection =
                    frame.getUpdatedTrackables(AugmentedImage.class);

            // There is at least one augmented image detected (Last known Pos, or Full-Tracking)
            for (AugmentedImage augmentedImage : updatedAiCollection) {

                if(augmentedImage.getTrackingMethod()== TrackingMethod.FULL_TRACKING) {

                    //Log.d("Anim Exception", "PausedStopped, full-tracking detected for: "+ augmentedImage.getName());

                    if (!augmentedImageMap.containsKey(augmentedImage.getName())) {

                        currentRenderableName = augmentedImage.getName();


                        // ---

                        removeAugmentedMapBut(augmentedImage.getName());

                        removeAllNodes();

                        // ---



                        fitToScanView.setVisibility(View.GONE);



                        int renderableID = Integer.parseInt(augmentedImage.getName().trim());

                        Log.d("Anim Exception", "PausedStopped, Now setting node for: "+ augmentedImage.getName());

                        ArSceneAnchorNode node = new ArSceneAnchorNode(ArActivity.this);
                        node.setRenderableUsingImage(augmentedImage);

                        augmentedImageMap.put(augmentedImage.getName(), node);
                        arFragment.getArSceneView().getScene().addChild(node);

                        try {
                            if (animators.get(renderableID) != null) {
                                if(animators.get(renderableID).isRunning()) {
                                    //animators.get(renderableID).cancel();
                                    animators.get(renderableID).setRepeatCount(ModelAnimator.INFINITE);
                                    animators.get(renderableID).start();
                                } else {
                                    animators.get(renderableID).setRepeatCount(ModelAnimator.INFINITE);
                                    animators.get(renderableID).start();
                                }
                            }
                        } catch (Exception ex) {
                            Log.d("Anim Exception", "Tracking-Animators Anim Exception Msg:"+ex);
                        }

                        Log.d("Anim Exception", "PausedStopped, Done setting node for: "+ augmentedImage.getName());


                    }

                    // Just process the first full-tracking augmented image and skip the frame
                    return;
                }
                else { //last-known-pos, not-tracking
                   //Log.d("Anim Exception", "PausedStopped, (else)detected for: "+ augmentedImage.getName() + " "+ augmentedImage.getTrackingMethod());

                    //if(augmentedImage.getTrackingState()==TrackingState.STOPPED)
                        //augmentedImageMap.remove(augmentedImage.getName());

                    if(!augmentedImage.getName().equalsIgnoreCase(currentRenderableName))//augmentedImage.getTrackingState()!=TrackingState.TRACKING) {
                    {
                        /*try {
                            for (Anchor anc : augmentedImage.getAnchors())
                                anc.detach();
                        }
                        catch (Exception ex) {}

                        try {
                        arFragment.getArSceneView().getScene()
                                .removeChild(augmentedImageMap.get(augmentedImage));
                        }
                        catch (Exception ex) {}*/



                        //if(augmentedImage.getTrackingState()==TrackingState.STOPPED)
                        //augmentedImageMap.remove(augmentedImage.getName());
                    }
                }
            }




            /*for(Anchor anchor: frame.getUpdatedAnchors())
            {
                if(anchor.getTrackingState()== TrackingState.PAUSED
                || anchor.getTrackingState()== TrackingState.STOPPED)
                    anchor.detach();
            }
             */



            Collection<AugmentedImage> updatedAugmentedImages =
                    frame.getUpdatedTrackables(AugmentedImage.class);

            // temporary return , remove it later

            updatedAugmentedImages.clear();

            // ----------------------------------





            for (AugmentedImage augmentedImage : updatedAugmentedImages) {







                int renderableID = Integer.parseInt(augmentedImage.getName().trim());

                switch (augmentedImage.getTrackingState()) {

                    case PAUSED:

                        // Allow no new augmented images
                        if(true) {

                            Log.d("Anim Exception", "PausedStopped- Pause: Tracking method for image:" + augmentedImage.getName());

                            //onPausedTrackable(augmentedImage, renderableID);

                            /*try {
                                if (animators.get(renderableID) != null) {
                                    Log.d("Anim Exception", "PausedStopped-Cancel Anim Not Null");

                                    animators.get(renderableID).pause();
                                    animators.get(renderableID).end();
                                    animators.get(renderableID).cancel();
                                } else
                                    Log.d("Anim Exception", "PausedStopped-Cancel Anim Null");
                            } catch (Exception ex) {
                                Log.d("Anim Exception", "PausedStopped-Cancel Anim Exception Msg:" + ex);
                            }*/

                            try {
                            /*for(Anchor anc : augmentedImage.getAnchors())
                            {
                                anc.detach();

                            }
                             */

                                //augmentedImageMap.get(augmentedImage).
                                //arFragment.getArSceneView().getScene()
                                //.removeChild(augmentedImageMap.get(augmentedImage));

                                /*for (String i : augmentedImageMap.keySet()) {
                                    if (augmentedImageMap.get(i) == null)
                                        Log.d("Anim Exception", "PausedStopped image name:" + i + " / Value is Null");
                                    else
                                        Log.d("Anim Exception", "PausedStopped image name:" + i + " / Value Okay / ");
                                }*/

                                ArSceneAnchorNode arSceneAnchor = augmentedImageMap.get(augmentedImage.getName());
                                if(arSceneAnchor==null)
                                    Log.d("Anim Exception", "PausedStopped arSceneAnchor is Null");
                                else
                                    Log.d("Anim Exception", "PausedStopped arSceneAnchor size:" + arSceneAnchor.getChildren().size() + " / Value Okay / ");


                                try {
                                    List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());//augmentedImageMap.get(augmentedImage).getChildren());
                                    for (Node node : children) {
                                        if (node instanceof AnchorNode) {
                                            if (((AnchorNode) node).getAnchor() != null) {
                                                ((AnchorNode) node).getAnchor().detach();
                                            }
                                        }

                                        if (!(node instanceof Camera) && !(node instanceof Sun)) {
                                            node.setParent(null);
                                        }
                                    }
                                } catch (Exception ex) {
                                    Log.d("Anim Exception", "PausedStopped-RemoveChild Anim Exception Msg:" + ex);
                                }

                            /*for(Anchor anc : augmentedImage.getAnchors())
                            {
                                anc.detach();

                            }*/


                            } catch (Exception ex) {
                                Log.d("Anim Exception", "PausedStopped-RemoveChild Anim Exception Msg:" + ex);
                            }

                            Log.d("Anim Exception", "PausedStopped-AugImage Paused:" + augmentedImage.getName());

                            //augmentedImageMap.remove(augmentedImage.getName());

                            augmentedImageMap.remove(augmentedImage.getName());
                        }
                        else
                            Log.d("Anim Exception", "PausedStopped- Pause: (Else)Tracking:" + augmentedImage.getTrackingMethod());

                        break;

                    case TRACKING:

                        // Have to switch to UI Thread to update View.
                        fitToScanView.setVisibility(View.GONE);

                        //Log.d("Anim Exception", "PausedStopped, Trying to set node for: "+ augmentedImage.getName());

                        // Create a new anchor for newly found images.
                        if (!augmentedImageMap.containsKey(augmentedImage.getName())) {

                            Log.d("Anim Exception", "PausedStopped, Now setting node for: "+ augmentedImage.getName());

                            ArSceneAnchorNode node = new ArSceneAnchorNode(ArActivity.this);
                            node.setRenderableUsingImage(augmentedImage);

                            augmentedImageMap.put(augmentedImage.getName(), node);
                            arFragment.getArSceneView().getScene().addChild(node);

                            try {
                                if (animators.get(renderableID) != null) {
                                    if(animators.get(renderableID).isRunning()) {
                                        animators.get(renderableID).cancel();
                                        animators.get(renderableID).setRepeatCount(ModelAnimator.INFINITE);
                                        animators.get(renderableID).start();
                                    } else {
                                        animators.get(renderableID).setRepeatCount(ModelAnimator.INFINITE);
                                        animators.get(renderableID).start();
                                    }
                                }
                            } catch (Exception ex) {
                                Log.d("Anim Exception", "Tracking-Animators Anim Exception Msg:"+ex);
                            }
                            Log.d("Anim Exception", "PausedStopped, Done setting node for: "+ augmentedImage.getName());


                        }
                        break;

                    case STOPPED:

                        Log.d("Anim Exception", "PausedStopped, AugImage Stopped:"+ augmentedImage.getName());

                        augmentedImageMap.remove(augmentedImage.getName());

                        break;
                }
            }
        }catch (Exception e){
            Log.d("frame exception","frame exception: "+ e);
        }
    }

    private  void removeAllNodes(){
        try {
            List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
            for (Node node : children) {
                if (node instanceof AnchorNode) {
                    if (((AnchorNode) node).getAnchor() != null) {
                        ((AnchorNode) node).getAnchor().detach();
                    }
                }

                if (!(node instanceof Camera) && !(node instanceof Sun)) {
                    node.setParent(null);
                }
            }
        } catch (Exception ex) {
            Log.d("Anim Exception", "PausedStopped-RemoveChild Anim Exception Msg:" + ex);
        }
    }

    private void removeAugmentedMapBut(String renderableName){
        for(String i : augmentedImageMap.keySet())
        {
            if(!i.equalsIgnoreCase(renderableName)){
                try {
                    augmentedImageMap.get(i).getAnchor().detach();

                    for (Node node : augmentedImageMap.get(i).getChildren()) {
                        if (!(node instanceof Camera) && !(node instanceof Sun)) {
                            node.setParent(null);
                        }
                    }

                }catch (Exception ex){}

                augmentedImageMap.remove(i);
            }
        }
    }


    private  void onPausedOrStoppedTrackable(AugmentedImage augmentedImg, int renderableId){

        try {
            if (animators.get(renderableId) != null) {
                Log.d("Anim Exception", "PausedStopped-Cancel Anim Not Null");

                animators.get(renderableId).pause();
                animators.get(renderableId).end();
                animators.get(renderableId).cancel();
            }
            else
                Log.d("Anim Exception", "PausedStopped-Cancel Anim Null");
        } catch (Exception ex) {
            Log.d("Anim Exception", "PausedStopped-Cancel Anim Exception Msg:"+ex);
        }

        try {
            for(Anchor anc : augmentedImg.getAnchors())
            {
                anc.detach();
            }

            arFragment.getArSceneView().getScene()
                    .removeChild(augmentedImageMap.get(augmentedImg.getName()));
        }catch (Exception ex) {
            Log.d("Anim Exception", "PausedStopped-RemoveChild Anim Exception Msg:"+ex);
        }

        augmentedImageMap.remove(augmentedImg.getName());
    }

    private void disablePlaneMechanism() {
        if(arFragment!=null)
        {
            arFragment.getPlaneDiscoveryController().hide();
            arFragment.getPlaneDiscoveryController().setInstructionView(null);
            arFragment.getArSceneView().getPlaneRenderer().setEnabled(false);
        }
    }

    private void createModelRenderables() {
        // When you build a Renderable, Sceneform loads its resources in the background while returning
        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().

        /*
        ModelRenderable.builder()
                .setSource(this, R.raw.andy)
                .build()
                .thenAccept(renderable -> andyRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });*/


        /*String glbAsset1 = arRootPath + "baby.glb";
        ModelRenderable.builder()
                .setSource(ArActivity.this, RenderableSource.builder().setSource(
                        ArActivity.this,
                        Uri.parse(glbAsset1),
                        RenderableSource.SourceType.GLB)
                        .setScale(charScaleFactor)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(glbAsset1)
                .build()
                .thenAccept(renderable -> renderableChars.add(renderable));*/


        /*String glbAsset2 = arRootPath + "boy.glb";
        ModelRenderable.builder()
                .setSource(ArActivity.this, RenderableSource.builder().setSource(
                        ArActivity.this,
                        Uri.parse(glbAsset2),
                        RenderableSource.SourceType.GLB)
                        .setScale(charScaleFactor)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(glbAsset2)
                .build()
                .thenAccept(renderable -> renderableChars.add(renderable));*/

        /*String glbAsset3 = arRootPath + "boy2.glb";
        ModelRenderable.builder()
                .setSource(ArActivity.this, RenderableSource.builder().setSource(
                        ArActivity.this,
                        Uri.parse(glbAsset3),
                        RenderableSource.SourceType.GLB)
                        .setScale(charScaleFactor)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(glbAsset3)
                .build()
                .thenAccept(renderable -> renderableChars.add(renderable));*/

        /*String glbAsset4 = arRootPath + "girl.glb";
        ModelRenderable.builder()
                .setSource(ArActivity.this, RenderableSource.builder().setSource(
                        ArActivity.this,
                        Uri.parse(glbAsset4),
                        RenderableSource.SourceType.GLB)
                        .setScale(charScaleFactor)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(glbAsset4)
                .build()
                .thenAccept(renderable -> renderableChars.add(renderable));*/


        /*String glbAsset5 = arRootPath + "man.glb";
        ModelRenderable.builder()
                .setSource(ArActivity.this, RenderableSource.builder().setSource(
                        ArActivity.this,
                        Uri.parse(glbAsset5),
                        RenderableSource.SourceType.GLB)
                        .setScale(charScaleFactor)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(glbAsset5)
                .build()
                .thenAccept(renderable -> renderableChars.add(renderable));*/


        /*String glbAsset6 = arRootPath + "woman.glb";
        ModelRenderable.builder()
                .setSource(ArActivity.this, RenderableSource.builder().setSource(
                        ArActivity.this,
                        Uri.parse(glbAsset6),
                        RenderableSource.SourceType.GLB)
                        .setScale(charScaleFactor)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(glbAsset6)
                .build()
                .thenAccept(renderable -> renderableChars.add(renderable));*/


        //renderableChars.get(0).


        /*arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (renderableChars.isEmpty()){//andyRenderable == null) {
                        return;
                    }

                    if(currentNumOfSceneChars>maxNumOfChars-1)
                    {
                        return;
                    }

                    int nextNumber = getNextNumber();
                    // if(renderableChars.get(nextNumber).isDone() == false)
                    //   return;

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    // Create the transformable andy and add it to the anchor.
                    TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                    andy.setParent(anchorNode);
                    //andy.setRenderable(renderableChars.get(ArSystem.getRandomIntegerBetweenRange(0,renderableChars.size()-1)));//andyRenderable);
                    //andy.setRenderable(renderableChars.get(nextNumber).getNow(null));//andyRenderable);
                    andy.setRenderable(renderableChars.get(nextNumber));//andyRenderable);

                    andy.select();

                    currentNumOfSceneChars++;

                });*/
    }


    @Override
    public void onPause() {
        super.onPause();

        //saveAugments in ArSystem
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();


        ArSystem.activityStackNumber = 2;


        //https://developers.google.com/ar/develop/java/enable-arcore#request_camera_permission_ar_optional_and_ar_required_apps

        // ARCore requires camera permission to operate.
        if (!ArCameraPermissionHelper.hasCameraPermission(this)) {
            ArCameraPermissionHelper.requestCameraPermission(this);
            return;
        }

        // retrieveAugments from ArSystem
        // if the system is already created, if yet, retrieve it


        if (augmentedImageMap.isEmpty()) {
            fitToScanView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {

        //https://developers.google.com/ar/develop/java/enable-arcore#request_camera_permission_ar_optional_and_ar_required_apps

        if (!ArCameraPermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(this, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
                    .show();
            if (!ArCameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                ArCameraPermissionHelper.launchPermissionSettings(this);
            }
            finish();
        }
    }


    void setRenderable(int id, ModelRenderable renderable) {
        try {
            this.renderables.put(id,renderable);

            if(this.animators.containsKey(id)) {
                if(this.animators.get(id)==null)
                {
                    AnimationData data = renderable.getAnimationData(0);
                    this.animators.put(id, new ModelAnimator(data,renderable));
                }
            }
            else {
                AnimationData data = renderable.getAnimationData(0);
                this.animators.put(id, new ModelAnimator(data, renderable));
            }


        }
        catch (Exception ex){
            Log.e("Anim Exception", "Anim Exception Msg:"+ex);
        }
    }

    void onException(int id, Throwable throwable) {
        Toast toast = Toast.makeText(this, "Unable to load renderable: " + id, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Log.e(TAG, "Unable to load andy renderable", throwable);
    }



}
