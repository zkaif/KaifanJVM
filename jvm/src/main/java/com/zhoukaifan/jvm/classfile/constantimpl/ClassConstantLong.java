package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantLong extends ClassConstant {
    private long content;

    public ClassConstantLong(long content) {
        super((byte) 5);
        this.content = content;
    }
}
