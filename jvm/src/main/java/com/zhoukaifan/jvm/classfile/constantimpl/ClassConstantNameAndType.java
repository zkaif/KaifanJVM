package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantNameAndType extends ClassConstant {

    private short nameIndex;
    private short descriptorIndex;

    public ClassConstantNameAndType(short nameIndex, short descriptorIndex) {
        super((byte) 12);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
