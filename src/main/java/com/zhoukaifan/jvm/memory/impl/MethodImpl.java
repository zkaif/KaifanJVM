package com.zhoukaifan.jvm.memory.impl;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.memory.IMethod;

import java.util.Map;

public class MethodImpl implements IMethod {
    private Map<String, ClassFile> classFileMap;
}
