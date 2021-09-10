package com.zhoukaifan.jvm.obj;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.utils.FieldUtils;

import java.nio.ByteBuffer;

public class ObjInstance {
    private boolean array;
    private int arrayLength;
    private String arrayType;
    private ClassFile classFile;
    private byte[] data;
    private ByteBuffer byteBuffer;

    public ObjInstance() {
    }

    public ObjInstance(ClassFile classFile) {
        this.array = false;
        this.classFile = classFile;
        this.data = new byte[classFile.getFieldSizeForBytes()];
        byteBuffer = ByteBuffer.wrap(data);
    }
    public ObjInstance(boolean array, int arrayLength, String arrayType) {
        this.array = array;
        this.arrayLength = arrayLength;
        this.arrayType = arrayType;
        this.data = new byte[arrayLength * FieldUtils.getSizeForBytes(arrayType)];
        byteBuffer = ByteBuffer.wrap(data);
    }

    public ClassFile getClassFile() {
        return classFile;
    }

    public void setClassFile(ClassFile classFile) {
        this.classFile = classFile;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public String getArrayType() {
        return arrayType;
    }

    public void setArrayType(String arrayType) {
        this.arrayType = arrayType;
    }

    public void copy(byte[] bytes){
        System.arraycopy(bytes,0,data,0,bytes.length);
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    public void setByteBuffer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }
}
