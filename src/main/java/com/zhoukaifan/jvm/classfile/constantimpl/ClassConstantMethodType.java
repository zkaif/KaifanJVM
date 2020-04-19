package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantMethodType extends ClassConstant {

    private short descriptorIndex;

    public ClassConstantMethodType(short descriptorIndex) {
        super((byte) 16);
        this.descriptorIndex = descriptorIndex;
    }
}
