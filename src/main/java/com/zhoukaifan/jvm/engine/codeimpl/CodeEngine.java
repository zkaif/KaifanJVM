package com.zhoukaifan.jvm.engine.codeimpl;

import com.zhoukaifan.jvm.classfile.Attributes;
import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classfile.Method;
import com.zhoukaifan.jvm.classfile.attributesimpl.AttributesCode;
import com.zhoukaifan.jvm.engine.CommandStratagy;
import com.zhoukaifan.jvm.engine.ICodeEngine;
import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CodeEngine implements ICodeEngine {
    private SharedData sharedData;
    private ThreadData threadData;
    private Counter counter;
    private Stack stack;

    @Override
    public void befor(ClassFile classFile, Method method, String threadId) {
        sharedData = RunTimeData.getInstance().getSharedData();
        AttributesCode attributes = (AttributesCode) method.getAttributesMap().get("Code");
        threadData = new ThreadData();
        counter = threadData.getCounter();
        stack = threadData.getStack();
        Stack.Frame frame = new Stack.Frame(attributes.getMaxLocals(),attributes.getMaxStack());
        stack.push(frame);

        ByteBuffer byteBuffer = ByteBuffer.wrap(attributes.getCode());
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.position(0);
        counter.setByteBuffer(byteBuffer);
        counter.setClassFile(classFile);
        counter.setMethod(method);
    }

    @Override
    public void runCode() {
        while (counter.getByteBuffer().position()<counter.getByteBuffer().limit()){
            byte command = counter.getByteBuffer().get();
            ICommand byCommandCode = CommandStratagy.getByCommandCode(command);
            byCommandCode.run(threadData,counter,stack, stack.peek());
        }
    }

    private void after() {

    }
}
