package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantClass;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import lombok.Getter;

@Getter
public class ClassName {
    private short index;
    private ClassConstantClass classConstantClass;
    private ClassConstantUTF8 classConstantUTF8;

    public ClassName(short index, ClassConstantClass classConstantClass, ClassConstantUTF8 classConstantUTF8) {
        this.index = index;
        this.classConstantClass = classConstantClass;
        this.classConstantUTF8 = classConstantUTF8;
    }

    public String getClassName(){
        return classConstantUTF8.getContent();
    }
}
