package com.zhoukaifan.jvm.classpath;

import com.zhoukaifan.jvm.classfile.ClassFile;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface IClassPath {
    ClassFile getClassFileByClassName(String className) throws IOException;
}
