package com.zavosh.itfamily.activities.ar.arfile;

import java.io.File;

public class ArFile {

    public File file;

    public enum ArFileType
    {
        RefImageDatabase, Scenario, ReferenceImage, Photo, Video, Model3D, Sound
    }

    public ArFileType fileType;

}


