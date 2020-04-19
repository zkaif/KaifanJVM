package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantString extends ClassConstant {

    private short stringIndex;

    public ClassConstantString(short stringIndex) {
        super((byte) 8);
        this.stringIndex = stringIndex;
    }
}
