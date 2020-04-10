package com.zavosh.itfamili.activities.ar.arfile;

public class ArFileLevel2 extends ArFile {

    /**
     * The index of the file. There might be more than one ar file (ex. 3d) associated with a certain reference image.
     */
    public int index =0;

    /**
     * reference number : a number that links refrence images to ar content (photo, video, 3d, sound, ...)
     */
    public int refnum =0;

    /**
     * Update Version
     */
    public int uv =0;

    /**
     * Magazine Publish Number
     */
    public int mag =0;

}


