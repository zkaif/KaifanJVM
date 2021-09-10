package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantFloat;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantInteger;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantString;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.RunTimeData;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;

public class CmdLdc implements ICommand {
    public static ICommand CMD = new CmdLdc();
    @Override
    public void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek) {
        byte index = counter.getByteBuffer().get();
        ClassConstant constant = counter.getClassFile().getConstants()[index];
        if (constant instanceof ClassConstantString){
            ClassConstantUTF8 classConstantUTF8 = (ClassConstantUTF8) counter.getClassFile().getConstants()[((ClassConstantString) constant).getStringIndex()];
            long stringObjIndex = ((ClassConstantString) constant).getStringObj(classConstantUTF8);
            peek.getOperandStack().push(stringObjIndex);
            return;
        }
        if (constant instanceof ClassConstantInteger){
            int content = ((ClassConstantInteger) constant).getContent();
            peek.getOperandStack().push(content);
        }
        if (constant instanceof ClassConstantFloat){
            float content = ((ClassConstantFloat) constant).getContent();
            peek.getOperandStack().push(content);
        }
    }
}
