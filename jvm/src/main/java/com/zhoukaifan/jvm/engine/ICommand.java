package com.zhoukaifan.jvm.engine;

import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;

public interface ICommand {
    void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek);
}
