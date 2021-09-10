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

    public void putCounter(Counter counter1) {
        byteBuffer = counter1.byteBuffer;
        classFile = counter1.classFile;
        method = counter1.method;
    }

    public Counter copyCounter() {
        Counter counter = new Counter();
        counter.setByteBuffer(byteBuffer);
        counter.setClassFile(classFile);
        counter.setMethod(method);
        return counter;
    }
}
