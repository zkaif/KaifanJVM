package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantMethoderf extends ClassConstant {

    private short classIndex;
    private short nameAndTypeIndex;

    public ClassConstantMethoderf(short classIndex, short nameAndTypeIndex) {
        super((byte) 10);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
