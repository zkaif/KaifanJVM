package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantMethodHandle extends ClassConstant {

    private byte referenceKind;
    private short referenceIndex;

    public ClassConstantMethodHandle(byte referenceKind, short referenceIndex) {
        super((byte) 15);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
}
