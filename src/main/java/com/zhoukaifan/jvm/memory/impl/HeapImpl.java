package com.zhoukaifan.jvm.memory.impl;

import com.zhoukaifan.jvm.memory.IHeap;

import java.util.Map;

public class HeapImpl implements IHeap {
    private Map<Integer,Object> heapData;
}
