package com.zhoukaifan.jvm.utils;

import java.util.ArrayList;
import java.util.List;

public class MethodUtils {
    public static Class[] descToClassArray(String desc){
        List<Class> classList = new ArrayList<>();
        String preDesc = desc.split("\\)")[0];
        preDesc = preDesc.replace("(","");
        char[] chars = preDesc.toCharArray();
        for (int i = 0;i<chars.length;++i){
            char typeChar = chars[i];
            switch (typeChar){
                case 'B':
                    classList.add(byte.class);
                    break;
                case 'C':
                    classList.add(char.class);
                    break;
                case 'D':
                    classList.add(double.class);
                    break;
                case 'F':
                    classList.add(float.class);
                    break;
                case 'I':
                    classList.add(int.class);
                    break;
                case 'J':
                    classList.add(long.class);
                    break;
                case 'S':
                    classList.add(short.class);
                    break;
                case 'Z':
                    classList.add(boolean.class);
                    break;
            }
        }

        Class[] classes = new Class[classList.size()];
        return classList.toArray(classes);
    }

    public static List<String> parseDesc(String content) {
        List<String> paramList = new ArrayList<>();
        char[] chars = content.toCharArray();
        for (int n = 0;n<chars.length;n++){
            char type = chars[n];
            switch (type){
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                    paramList.add(type+"");
                    break;
                case 'L':
                    String l = type+"";
                    do{
                        n++;
                        l = l + chars[n];
                    }
                    while(chars[n]==';');
                    break;
                case '[':
                    String arrayType = type+"";
                    n++;
                    arrayType = arrayType + chars[n];
                    paramList.add(arrayType);
                    break;
            }
        }
        return paramList;
    }

    public static Class vmTypeToNativeClass(String type) {
        char c = type.toCharArray()[0];
        switch (c){
            case 'B':{
                return byte.class;
            }
            case 'C':{
                return char.class;
            }
            case 'D':{
                return double.class;
            }
            case 'F':{
                return float.class;
            }
            case 'I':{
                return int.class;
            }
            case 'J':{
                return long.class;
            }
            case 'L':{
                return long.class;
            }
            case 'S':{
                return short.class;
            }
            case 'Z':{
                return boolean.class;
            }
            case '[':{
                return long.class;
//                char cc = type.toCharArray()[1];
//                switch (cc){
//                    case 'B':{
//                        return byte[].class;
//                    }
//                    case 'C':{
//                        return char[].class;
//                    }
//                    case 'D':{
//                        return double[].class;
//                    }
//                    case 'F':{
//                        return float[].class;
//                    }
//                    case 'I':{
//                        return int[].class;
//                    }
//                    case 'J':{
//                        return long[].class;
//                    }
//                    case 'L':{
//                        return long[].class;
//                    }
//                    case 'S':{
//                        return short[].class;
//                    }
//                    case 'Z':{
//                        return boolean[].class;
//                    }
//                }
            }
        }
        return null;
    }
}
