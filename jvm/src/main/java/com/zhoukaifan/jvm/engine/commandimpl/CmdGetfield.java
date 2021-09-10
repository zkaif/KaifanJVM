package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.Field;
import com.zhoukaifan.jvm.classfile.constantimpl.*;
import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.RunTimeData;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;
import com.zhoukaifan.jvm.obj.ObjInstance;

import java.nio.ByteBuffer;

public class CmdGetfield implements ICommand {
    public static ICommand CMD = new CmdGetfield();
    @Override
    public void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek) {
        short index = counter.getByteBuffer().getShort();
        ClassConstant[] constants = counter.getClassFile().getConstants();
        ClassConstantFieldref classConstantFieldref = (ClassConstantFieldref) constants[index];
        ClassConstantNameAndType classConstantNameAndType = (ClassConstantNameAndType) constants[classConstantFieldref.getNameAndTypeIndex()];
        ClassConstantUTF8 classConstantUTF8Name = (ClassConstantUTF8) constants[classConstantNameAndType.getNameIndex()];
        Field field = counter.getClassFile().getFieldByName(classConstantUTF8Name);
        int fieldBytesIndex = field.getBytesIndex();
        long thisIndex = (long) peek.getOperandStack().pop();
        ObjInstance objInstance = RunTimeData.getInstance().getSharedData().getBytesByIndex(thisIndex);
        ByteBuffer byteBuffer = objInstance.getByteBuffer();
        byteBuffer.position(fieldBytesIndex);

        String content = field.getDesConstant().getContent();
        char c = content.toCharArray()[0];
        switch (c){
            case 'B':{
                peek.getOperandStack().push(byteBuffer.get());
            }
            case 'C':{
                peek.getOperandStack().push(byteBuffer.getChar());
            }
            case 'D':{
                peek.getOperandStack().push(byteBuffer.getDouble());
            }
            case 'F':{
                peek.getOperandStack().push(byteBuffer.getFloat());
            }
            case 'I':{
                peek.getOperandStack().push(byteBuffer.getInt());
            }
            case 'J':{
                peek.getOperandStack().push(byteBuffer.getLong());
            }
            case 'L':{
                peek.getOperandStack().push(byteBuffer.getLong());
            }
            case 'S':{
                peek.getOperandStack().push(byteBuffer.getShort());
            }
            case 'Z':{
                peek.getOperandStack().push(byteBuffer.get()==0);
            }
            case '[':{
                peek.getOperandStack().push(byteBuffer.getLong());
            }
        }
    }
}
