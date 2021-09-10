package com.zhoukaifan.jvm.utils;

public class FieldUtils {
    public static int getSizeForBytes(String desc){
        char c = desc.toCharArray()[0];
        switch (c){
            case 'B':{
                return 1;
            }
            case 'C':{
                return 2;
            }
            case 'D':{
                return 8;
            }
            case 'F':{
                return 4;
            }
            case 'I':{
                return 4;
            }
            case 'J':{
                return 8;
            }
            case 'L':{
                return 8;
            }
            case 'S':{
                return 2;
            }
            case 'Z':{
                return 1;
            }
            case '[':{
                return 8;
            }
        }
        return 0;
    }
}
