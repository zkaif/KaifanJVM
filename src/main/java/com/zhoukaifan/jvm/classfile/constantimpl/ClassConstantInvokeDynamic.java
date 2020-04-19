package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantInvokeDynamic extends ClassConstant {

    private short bootstrapMethodAttrIndex;
    private short nameAndTypeIndex;

    public ClassConstantInvokeDynamic(short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
        super((byte) 18);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
