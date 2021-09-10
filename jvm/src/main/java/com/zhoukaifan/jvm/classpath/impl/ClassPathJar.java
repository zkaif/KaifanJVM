package com.zhoukaifan.jvm.classpath.impl;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classpath.IClassPath;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class ClassPathJar implements IClassPath {
    private JarFile jarFile;

    public static ClassPathJar getClassPathJar(String jarFilePath) {
        ClassPathJar classPathJar = new ClassPathJar();
        try {
            classPathJar.jarFile = new JarFile(jarFilePath);
        } catch (IOException e) {
            log.error("IOException:",e);
            return null;
        }
        return classPathJar;
    }

    @Override
    public ClassFile getClassFileByClassName(String className) {
        JarEntry jarEntry = jarFile.getJarEntry(className + ".class");
        if (jarEntry==null){
            return null;
        }
        ByteBuffer byteBuffer = null;
        try {
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            byteBuffer = ByteBuffer.wrap(bytes);
        } catch (IOException e) {
            log.error("IOException",e);
        }
        return ClassFile.createClassFile(byteBuffer);
    }

    public JarFile getJarFile() {
        return jarFile;
    }
}
