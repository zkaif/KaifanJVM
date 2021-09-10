package com.zhoukaifan.jvm.memory;

import com.zhoukaifan.jvm.obj.ObjInstance;

public interface IHeap {
    ObjInstance getData(long index);

    long putData(ObjInstance bytes);
}
