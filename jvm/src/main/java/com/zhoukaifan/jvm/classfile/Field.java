package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import com.zhoukaifan.jvm.utils.FieldUtils;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public class Field {
    private AccessFlagsField flags;
    private ClassConstantUTF8 nameConstant;
    private ClassConstantUTF8 desConstant;
    private Attributes[] attributes;
    private int bytesIndex;

    public static Field createByByteBuffer(ByteBuffer byteBuffer, ClassConstant[] classConstants, int bytesIndex) {
        Field field = new Field();
        field.bytesIndex = bytesIndex;
        short flag = byteBuffer.getShort();
        AccessFlagsField accessFlagsField = new AccessFlagsField(flag);
        field.flags = accessFlagsField;
        short nameIndex = byteBuffer.getShort();
        field.nameConstant = (ClassConstantUTF8) classConstants[nameIndex];
        short desIndex = byteBuffer.getShort();
        field.desConstant = (ClassConstantUTF8) classConstants[desIndex];
        short attributesCount = byteBuffer.getShort();
        field.attributes = new Attributes[attributesCount];
        for (int i = 0;i<attributesCount;++i) {
            Attributes attributes = Attributes.createByByteBuffer(byteBuffer,classConstants);
            field.attributes[i] = attributes;
        }
        return field;
    }

    public int getSizeForBytes(){
        String content = desConstant.getContent();
        return FieldUtils.getSizeForBytes(content);
    }

    public int getBytesIndex() {
        return bytesIndex;
    }
}
