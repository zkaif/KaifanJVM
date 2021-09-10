package com.zhoukaifan.jvm.memory;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.obj.ObjInstance;

import java.util.Map;

public interface IMethod {

    Map<String, ClassFile> getClassFileMap();

    ObjInstance getStaticData(long index);

    long putStaticData(ObjInstance objInstance);

}
