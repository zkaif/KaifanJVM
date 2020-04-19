package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantClass;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public class InterfaceName {
    private short index;
    private ClassConstantClass classConstantClass;
    private ClassConstantUTF8 classConstantUTF8;

    public static InterfaceName createByByteBuffer(ByteBuffer byteBuffer, ClassConstant[] constants) {
        InterfaceName interfaceName = new InterfaceName();
        short classNameIndex = byteBuffer.getShort();
        ClassConstantClass classConstantClass = (ClassConstantClass) constants[classNameIndex];
        ClassConstantUTF8 classConstantUTF8 = (ClassConstantUTF8) constants[classConstantClass.getStringIndex()];
        interfaceName.index = classNameIndex;
        interfaceName.classConstantClass = classConstantClass;
        interfaceName.classConstantUTF8 = classConstantUTF8;
        return interfaceName;
    }
}
