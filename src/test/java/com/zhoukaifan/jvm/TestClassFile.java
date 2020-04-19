package com.zhoukaifan.jvm;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classpath.impl.ClassPathJar;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.jar.JarEntry;

public class TestClassFile {
    @Test
    public void test1(){
        ClassPathJar classPathJar = ClassPathJar.getClassPathJar("/Users/dim/IdeaProjects/KaifanJVM/src/main/resources/Test.jar");
        Iterator<JarEntry> jarEntryIterator = classPathJar.getJarFile().entries().asIterator();
        while (jarEntryIterator.hasNext()){
            System.out.println(jarEntryIterator.next().toString());
        }
        ClassFile classFile = classPathJar.getClassFileByClassName("com/company/Main");
        System.out.println(classFile);
    }
}
