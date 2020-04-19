package com.zhoukaifan.jvm.classfile.attributesimpl;

import com.zhoukaifan.jvm.classfile.Attributes;
import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public class AttributesCode extends Attributes {
    private short maxStack;
    private short maxLocals;
    private int codeLength;
    private byte[] code;
    private short exceptionTableLength;
    private ExceptionTable[] exceptionTable;
    private short attributesCount;
    private Attributes[] attributes;
    protected AttributesCode(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        super(nameIndex,byteBuffer, classConstants);
    }

    public static AttributesCode createByByteBuffer(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        AttributesCode attributes = new AttributesCode(nameIndex,byteBuffer,classConstants);
        attributes.maxStack = byteBuffer.getShort();
        attributes.maxLocals = byteBuffer.getShort();
        attributes.codeLength = byteBuffer.getInt();
        byte[] code = new byte[attributes.codeLength];
        byteBuffer.get(code);
        attributes.code = code;
        attributes.exceptionTableLength = byteBuffer.getShort();
        ExceptionTable[] exceptionTables = new ExceptionTable[attributes.exceptionTableLength];
        for (int i = 0;i<attributes.exceptionTableLength;++i) {
            ExceptionTable exceptionTable = new ExceptionTable(
                    byteBuffer.getShort(), byteBuffer.getShort(),
                    byteBuffer.getShort(), byteBuffer.getShort()
            );
            exceptionTables[i] = exceptionTable;
        }
        attributes.exceptionTable = exceptionTables;
        attributes.attributesCount = byteBuffer.getShort();
        attributes.attributes = new Attributes[attributes.attributesCount];
        for (int i = 0;i<attributes.attributesCount;++i) {
            Attributes attributes1 = Attributes.createByByteBuffer(byteBuffer,classConstants);
            attributes.attributes[i] = attributes1;
        }
        return attributes;
    }

    @Getter
    public static class ExceptionTable {
        private short startPc;
        private short endPc;
        private short handlePc;
        private short catchType;

        public ExceptionTable(short startPc, short endPc, short handlePc, short catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlePc = handlePc;
            this.catchType = catchType;
        }
    }
}
