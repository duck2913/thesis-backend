package com.thesis.menu_service.util;

import java.io.File;

public class Util {
    public static void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}