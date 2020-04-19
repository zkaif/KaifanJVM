package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.attributesimpl.AttributesCode;
import com.zhoukaifan.jvm.classfile.attributesimpl.AttributesLineNumberTable;
import com.zhoukaifan.jvm.classfile.attributesimpl.AttributesLocalVariableTable;
import com.zhoukaifan.jvm.classfile.constantimpl.*;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public abstract class Attributes {
    protected ClassConstantUTF8 nameConstant;
    protected String name;
    protected int length;
    protected Attributes(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        this.nameConstant = (ClassConstantUTF8) classConstants[nameIndex];
        this.name = this.nameConstant.getContent();
        this.length = byteBuffer.getInt();
    }

    public static abstract class Builder {
        public abstract Attributes buildByByteBuffer(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants);

        public static class BuilderCode extends Builder {
            private static BuilderCode BUILDER = new BuilderCode();
            @Override
            public Attributes buildByByteBuffer(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants) {
                AttributesCode attributesCode = AttributesCode.createByByteBuffer(nameIndex,byteBuffer, classConstants);
                return attributesCode;
            }
        }
        public static class BuilderLineNumberTable extends Builder {
            private static BuilderLineNumberTable BUILDER = new BuilderLineNumberTable();
            @Override
            public Attributes buildByByteBuffer(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants) {
                AttributesLineNumberTable attributesLineNumberTable = AttributesLineNumberTable.createByByteBuffer(nameIndex,byteBuffer, classConstants);
                return attributesLineNumberTable;
            }
        }
        public static class BuilderLocalVariableTable extends Builder {
            private static BuilderLocalVariableTable BUILDER = new BuilderLocalVariableTable();
            @Override
            public Attributes buildByByteBuffer(short nameIndex,ByteBuffer byteBuffer, ClassConstant[] classConstants) {
                AttributesLocalVariableTable localVariableTable = AttributesLocalVariableTable.createByByteBuffer(nameIndex,byteBuffer, classConstants);
                return localVariableTable;
            }
        }
    }
    protected static Attributes createByByteBuffer(ByteBuffer byteBuffer, ClassConstant[] classConstants) {
        short attributesNameIndex = byteBuffer.getShort();
        ClassConstantUTF8 classConstant = (ClassConstantUTF8) classConstants[attributesNameIndex];
        String type = classConstant.getContent();
        switch (type){
            case "Code":
                return Builder.BuilderCode.BUILDER.buildByByteBuffer(attributesNameIndex,byteBuffer,classConstants);
            case "LineNumberTable":
                return Builder.BuilderLineNumberTable.BUILDER.buildByByteBuffer(attributesNameIndex,byteBuffer,classConstants);
            case "LocalVariableTable":
                return Builder.BuilderLocalVariableTable.BUILDER.buildByByteBuffer(attributesNameIndex,byteBuffer,classConstants);
            default:
                return null;
        }
    }
}
