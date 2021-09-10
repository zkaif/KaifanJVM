package com.zhoukaifan.jvm.memory.impl;

import com.zhoukaifan.jvm.memory.IHeap;
import com.zhoukaifan.jvm.obj.ObjInstance;

import java.util.Map;

public class HeapImpl implements IHeap {
    private Map<Long, ObjInstance> heapData;
    private long currentIndex = 0;

    @Override
    public ObjInstance getData(long index) {
        return heapData.get(index);
    }

    @Override
    public long putData(ObjInstance objInstance) {
        long currentIndex = ++this.currentIndex;
        heapData.put(currentIndex,objInstance);
        return currentIndex;
    }
}
