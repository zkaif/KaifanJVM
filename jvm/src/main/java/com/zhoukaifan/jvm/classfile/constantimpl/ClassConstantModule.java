package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantModule extends ClassConstant {

    private short nameIndex;

    public ClassConstantModule(short nameIndex) {
        super((byte) 19);
        this.nameIndex = nameIndex;
    }
}
