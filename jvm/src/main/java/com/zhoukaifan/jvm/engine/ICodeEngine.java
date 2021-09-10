package com.zhoukaifan.jvm.engine;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classfile.Method;

public interface ICodeEngine {
    void befor(ClassFile classFile, Method method, String threadId);

    void runCode();
}
