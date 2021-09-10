package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantDouble extends ClassConstant {
    private double content;

    public ClassConstantDouble(double content) {
        super((byte) 6);
        this.content = content;
    }
}
