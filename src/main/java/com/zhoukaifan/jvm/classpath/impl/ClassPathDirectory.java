package com.zhoukaifan.jvm.classpath.impl;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classpath.IClassPath;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

@Slf4j
public class ClassPathDirectory implements IClassPath {
    private File directory;

    public static ClassPathDirectory getClassPathDirectory(File directory) {
        ClassPathDirectory classPathDirectory = new ClassPathDirectory();
        classPathDirectory.directory = directory;
        return classPathDirectory;
    }

    @Override
    public ClassFile getClassFileByClassName(String className) {
        File file = new File(directory, className + ".class");
        if (!file.exists()) {
            return null;
        }
        ByteBuffer byteBuffer = null;
        try {
            FileChannel fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ);
            ByteBuffer byteBufferTmp = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBufferTmp);
            fileChannel.close();
            byteBuffer = byteBufferTmp;
        } catch (IOException e) {
            log.error("IOException:", e);
        }
        return ClassFile.createClassFile(byteBuffer);
    }
}
