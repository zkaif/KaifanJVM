package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantDynamic extends ClassConstant {

    private short bootstrapMethodAttrIndex;
    private short nameAndTypeIndex;

    public ClassConstantDynamic(short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
        super((byte) 17);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
