package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantFloat extends ClassConstant {
    private float content;

    public ClassConstantFloat(float content) {
        super((byte) 4);
        this.content = content;
    }
}
