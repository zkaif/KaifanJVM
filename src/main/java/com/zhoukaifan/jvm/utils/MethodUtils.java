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
}
