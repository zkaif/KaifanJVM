package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantClass extends ClassConstant {

    private short stringIndex;

    public ClassConstantClass(short stringIndex) {
        super((byte) 7);
        this.stringIndex = stringIndex;
    }
}
