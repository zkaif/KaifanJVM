package com.zhoukaifan.jvm.classfile.attributesimpl;

import com.zhoukaifan.jvm.classfile.Attributes;
import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import lombok.Getter;

import java.nio.ByteBuffer;

public class AttributesLocalVariableTable extends Attributes {

    private short localVariableTableLength;
    private LocalVariableTable[] localVariableTables;

    protected AttributesLocalVariableTable(short nameIndex, ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        super(nameIndex, byteBuffer, classConstants);
    }

    public static AttributesLocalVariableTable createByByteBuffer(short nameIndex, ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        AttributesLocalVariableTable attributesLocalVariableTable = new AttributesLocalVariableTable(nameIndex,byteBuffer,classConstants);
        attributesLocalVariableTable.localVariableTableLength = byteBuffer.getShort();
        attributesLocalVariableTable.localVariableTables = new LocalVariableTable[attributesLocalVariableTable.localVariableTableLength];
        for (int i = 0;i<attributesLocalVariableTable.localVariableTableLength;++i){
            attributesLocalVariableTable.localVariableTables[i] = new LocalVariableTable(
                    byteBuffer.getShort(),
                    byteBuffer.getShort(),
                    byteBuffer.getShort(),
                    byteBuffer.getShort(),
                    byteBuffer.getShort(),
                    classConstants
            );
        }
        return attributesLocalVariableTable;
    }

    @Getter
    public static class LocalVariableTable{
        private short startPc;
        private short length;
        private short nameIndex;
        private ClassConstantUTF8 nameUTF8;
        private String name;
        private short descriptorIndex;
        private ClassConstantUTF8 descriptorUTF8;
        private String descriptor;
        private short index;

        public LocalVariableTable(short startPc, short length, short nameIndex, short descriptorIndex, short index, ClassConstant[] classConstants) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
            this.nameUTF8 = (ClassConstantUTF8) classConstants[nameIndex];
            this.name = this.nameUTF8.getContent();
            this.descriptorUTF8 = (ClassConstantUTF8) classConstants[descriptorIndex];
            this.descriptor = this.descriptorUTF8.getContent();
        }
    }
}
