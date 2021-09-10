package com.zhoukaifan.jdk;

public class ConsolePrint {
    public native static void println(int i);
    public static void println(String s){
        println(s.toCharArray());
    }
    public native static void println(char[] chars);
}
