package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantFieldref extends ClassConstant {

    private short classIndex;
    private short nameAndTypeIndex;

    public ClassConstantFieldref(short classIndex, short nameAndTypeIndex) {
        super((byte) 9);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
