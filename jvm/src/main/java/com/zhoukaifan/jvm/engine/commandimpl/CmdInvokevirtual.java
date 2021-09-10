package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.classfile.AccessFlagsMethod;
import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classfile.Method;
import com.zhoukaifan.jvm.classfile.attributesimpl.AttributesCode;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantClass;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantMethoderf;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantNameAndType;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import com.zhoukaifan.jvm.classpath.impl.ClassPathControl;
import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;
import com.zhoukaifan.jvm.utils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CmdInvokevirtual implements ICommand {
    public static ICommand CMD = new CmdInvokevirtual();
    @Override
    public void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek) {
        short index = counter.getByteBuffer().getShort();
        ClassConstant[] constants = counter.getClassFile().getConstants();
        ClassConstantMethoderf classConstantMethoderf = (ClassConstantMethoderf) constants[index];
        short classIndex = classConstantMethoderf.getClassIndex();
        short nameAndTypeIndex = classConstantMethoderf.getNameAndTypeIndex();
        ClassConstantClass classConstantClass = (ClassConstantClass) constants[classIndex];
        short classConstantClassStringIndex = classConstantClass.getStringIndex();
        ClassConstantNameAndType classConstantNameAndType = (ClassConstantNameAndType) constants[nameAndTypeIndex];
        short classConstantNameAndTypeNameIndex = classConstantNameAndType.getNameIndex();
        short classConstantNameAndTypeDescriptorIndex = classConstantNameAndType.getDescriptorIndex();
        ClassConstantUTF8 className = (ClassConstantUTF8) constants[classConstantClassStringIndex];
        ClassConstantUTF8 methodName = (ClassConstantUTF8) constants[classConstantNameAndTypeNameIndex];
        ClassConstantUTF8 methodDesc = (ClassConstantUTF8) constants[classConstantNameAndTypeDescriptorIndex];
        ClassFile targetClassFile = ClassPathControl.getInstance().getClassFileByClassName(className.getContent());
        Method method = targetClassFile.getMethodMap().get(methodName.getContent() + ":" + methodDesc.getContent());

        AccessFlagsMethod accessFlagsMethod = method.getAccessFlagsMethod();
        if (accessFlagsMethod.isStatic()){
            //todo 后面要抛出异常
            return;
        }
        if (accessFlagsMethod.isNative()){
        } else {
            Long thisIndex = (Long) peek.getOperandStack().pop();
            AttributesCode attributes = (AttributesCode) method.getAttributesMap().get("Code");
            Stack.Frame frame = new Stack.Frame(attributes.getMaxLocals(),attributes.getMaxStack());
            stack.push(frame);
            frame.getLocalVariable().add(thisIndex);
            Counter counterBefore = counter.copyCounter();
            peek.getOperandStack().push(counterBefore);
            counter.setClassFile(targetClassFile);
            counter.setMethod(method);
            ByteBuffer byteBuffer = ByteBuffer.wrap(attributes.getCode());
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.position(0);
            counter.setByteBuffer(byteBuffer);
        }
    }
}
