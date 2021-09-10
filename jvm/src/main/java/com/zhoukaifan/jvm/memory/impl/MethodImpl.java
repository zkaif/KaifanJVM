package com.zhoukaifan.jvm.memory.impl;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.memory.IMethod;
import com.zhoukaifan.jvm.obj.ObjInstance;

import java.util.HashMap;
import java.util.Map;

public class MethodImpl implements IMethod {
    private Map<String, ClassFile> classFileMap = new HashMap<>();
    private Map<Long, ObjInstance> staticData = new HashMap<>();
    private long currentIndex = 0;

    @Override
    public Map<String, ClassFile> getClassFileMap() {
        return classFileMap;
    }

    @Override
    public ObjInstance getStaticData(long index) {
        return staticData.get(index);
    }

    @Override
    public long putStaticData(ObjInstance objInstance) {
        long currentIndex = --this.currentIndex;
        staticData.put(currentIndex,objInstance);
        return currentIndex;
    }
}
