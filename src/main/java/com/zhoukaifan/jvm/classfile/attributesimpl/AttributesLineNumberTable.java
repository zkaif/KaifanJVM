package com.zhoukaifan.jvm.classfile.attributesimpl;

import com.zhoukaifan.jvm.classfile.Attributes;
import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;

import java.nio.ByteBuffer;

public class AttributesLineNumberTable extends Attributes {

    private short lineNumberTableLength;
    private LineNumberTable[] lineNumberTables;

    protected AttributesLineNumberTable(short nameIndex, ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        super(nameIndex, byteBuffer, classConstants);
    }

    public static AttributesLineNumberTable createByByteBuffer(short nameIndex, ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        AttributesLineNumberTable attributesLineNumberTable = new AttributesLineNumberTable(nameIndex,byteBuffer,classConstants);
        attributesLineNumberTable.lineNumberTableLength = byteBuffer.getShort();
        attributesLineNumberTable.lineNumberTables = new LineNumberTable[attributesLineNumberTable.lineNumberTableLength];
        for (int i = 0;i<attributesLineNumberTable.lineNumberTableLength;++i){
            short startPc = byteBuffer.getShort();
            short lineNumber = byteBuffer.getShort();
            attributesLineNumberTable.lineNumberTables[i] = new LineNumberTable(startPc,lineNumber);
        }
        return attributesLineNumberTable;
    }

    @Getter
    public static class LineNumberTable{
        private short startPc;
        private short lineNumber;

        public LineNumberTable(short startPc, short lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
