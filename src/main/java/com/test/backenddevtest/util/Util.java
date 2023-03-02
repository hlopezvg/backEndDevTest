package com.test.backenddevtest.util;

import java.io.File;

public class Util {

    public static String healtCheck(){
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        File file = new File("/");
        Long freeSpace = file.getFreeSpace();

        return "{\"freeMemory\""+":"+"\""+freeMemory+"\"" +"," + "\"freeSpace:\""+ "\""+ freeSpace + "\"" +"}";
    }
}
