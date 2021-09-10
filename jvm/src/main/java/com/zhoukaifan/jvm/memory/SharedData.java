package com.zhoukaifan.jvm.memory;

import com.zhoukaifan.jvm.memory.impl.HeapImpl;
import com.zhoukaifan.jvm.memory.impl.MethodImpl;
import com.zhoukaifan.jvm.obj.ObjInstance;

public class SharedData {
    private IMethod method = new MethodImpl();
    private IHeap heap = new HeapImpl();

    public IMethod getMethod() {
        return method;
    }

    public void setMethod(IMethod method) {
        this.method = method;
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    public ObjInstance getBytesByIndex(long index){
        if (index<0){
            return method.getStaticData(index);
        }
        return heap.getData(index);
    }
}
