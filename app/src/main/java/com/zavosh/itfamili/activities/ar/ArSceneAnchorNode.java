package com.zavosh.itfamili.activities.ar;

import android.util.Log;

import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;


/**
 * Node for rendering an augmented image. The image is framed by placing the virtual picture frame
 * at the corners of the augmented image trackable.
 */
@SuppressWarnings({"AndroidApiChecker"})
public class ArSceneAnchorNode extends AnchorNode {

    private static final String TAG = "ArSceneAnchorNode";

    private AugmentedImage image;



    ArActivity activity;

    public ArSceneAnchorNode(ArActivity ctx) {

        this.activity = ctx;
    }

    /**
     * Called when the AugmentedImage is detected and should be rendered. A Sceneform node tree is
     * created based on an Anchor created from the image. The corners are then positioned based on the
     * extents of the image. There is no need to worry about world coordinates since everything is
     * relative to the center of the image, which is the parent node of the corners.
     */
    //@SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    public void setRenderableUsingImage(AugmentedImage image) {
        this.image = image;

        String filename = image.getName();
        Log.d("set renderable","set renderable incoming : name:"+filename);

        int renderableID = Integer.parseInt(filename.trim()); //Integer.parseInt(filename.trim().substring(0,filename.indexOf('.')).trim());

        Log.d("set renderable","set renderable incoming : "+renderableID+"  name:"+image.getName());

        if(this.activity.renderables.get(renderableID)!=null)
        {

            Log.d("set renderable","set renderable: "+renderableID);
            try {


                this.setAnchor(image.createAnchor(image.getCenterPose()));

                Node cornerNode = new Node();
                //cornerNode.setParent(this);
                cornerNode.setLocalPosition(new Vector3(0.0f,0.0f,0.0f));
                cornerNode.setLocalScale(new Vector3(0.1f,0.1f,0.1f));

                if(image.getName().equals("1"))
                    cornerNode.setLocalRotation(new Quaternion(Vector3.up(),180));

                cornerNode.setRenderable(this.activity.renderables.get(renderableID));
                this.addChild(cornerNode);



            }catch (Exception ex){
                Log.d("set renderable","set renderable exception: "+renderableID+ " msg:"+ex);
            }

        }
        else
        {
            //Toast toast = Toast.makeText(this.activity, "Loading.. " , Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.CENTER, 0, 0);
            //toast.show();
            Log.d("set renderable","set renderable not loaded yet: "+renderableID);
            return;
        }


    }

    public AugmentedImage getImage() {
        return image;
    }
}
