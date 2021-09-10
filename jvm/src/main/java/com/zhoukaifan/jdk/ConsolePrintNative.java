package com.zhoukaifan.jdk;

import com.zhoukaifan.jvm.memory.RunTimeData;
import com.zhoukaifan.jvm.obj.ObjInstance;

public class ConsolePrintNative {
    public static void println(int i){
        System.out.println(i);
    }
    public static void println(long chars){
        ObjInstance bytesByIndex = RunTimeData.getInstance().getSharedData().getBytesByIndex(chars);
        String s = new String(bytesByIndex.getData());
//        System.out.println(chars);
        System.out.println(s);
    }
}
