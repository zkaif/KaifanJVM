package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantInteger extends ClassConstant {
    private int content;

    public ClassConstantInteger(int content) {
        super((byte) 3);
        this.content = content;
    }
}
