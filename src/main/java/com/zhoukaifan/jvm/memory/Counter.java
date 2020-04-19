package com.zhoukaifan.jvm.memory;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classfile.Method;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class Counter {
    private ByteBuffer byteBuffer;
    private ClassFile classFile;
    private Method method;

}
