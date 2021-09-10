package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantPackage extends ClassConstant {

    private short nameIndex;

    public ClassConstantPackage(short nameIndex) {
        super((byte) 20);
        this.nameIndex = nameIndex;
    }
}
