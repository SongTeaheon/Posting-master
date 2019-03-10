package com.songtaeheon.posting.Utils;

import android.os.Environment;

public class FilePaths {

    public static String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    public static String PICTURES = ROOT_DIR + "/Pictures";
    public static String CAMERA = ROOT_DIR + "/DCIM/camera";
}
