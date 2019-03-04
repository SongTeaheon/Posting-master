package com.songtaeheon.posting.Utils;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {


    //dirctory 내부의 디렉토리 리스트를 리턴
    public static ArrayList<String> getDirectoryPaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listFiles = file.listFiles();
        if(listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    pathArray.add(listFiles[i].getAbsolutePath());
                }
            }
        }
        return pathArray;
    }

    //dirctory 내부의 파일 리스트를 리턴

    public static ArrayList<String> getFilePaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listFiles = file.listFiles();
        if(listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    pathArray.add(listFiles[i].getAbsolutePath());
                }
            }
        }
        return pathArray;

    }
}
