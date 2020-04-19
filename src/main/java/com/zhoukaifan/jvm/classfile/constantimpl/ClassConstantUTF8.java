package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantUTF8 extends ClassConstant {

    private short length;
    private String content;

    public ClassConstantUTF8(short length, String content) {
        super((byte) 1);
        this.length = length;
        this.content = content;
    }
}
