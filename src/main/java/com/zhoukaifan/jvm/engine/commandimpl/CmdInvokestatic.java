package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.classfile.AccessFlagsMethod;
import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classfile.Method;
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

public class CmdInvokestatic implements ICommand {
    public static ICommand CMD = new CmdInvokestatic();
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
        if (!accessFlagsMethod.isStatic()){
            //todo 后面要抛出异常
            return;
        }
        if (accessFlagsMethod.isNative()){
            Object pop = peek.getOperandStack().pop();
            String nativeClassName = className.getContent() + "Native";
            nativeClassName = nativeClassName.replace("/",".");
            Class<?> aClass;
            try {
                aClass = Class.forName(nativeClassName);
                Class[] classes = MethodUtils.descToClassArray(methodDesc.getContent());
                java.lang.reflect.Method method1 = aClass.getMethod(methodName.getContent(), classes);
                Object invoke = method1.invoke(null, pop);
                if (invoke!=null){
                    peek.getOperandStack().push(invoke);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                //todo 后面要抛出异常
                return;
            }
        } else {

        }
    }
}
