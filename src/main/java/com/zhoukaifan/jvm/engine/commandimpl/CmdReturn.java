package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;

public class CmdReturn implements ICommand {
    public static ICommand CMD = new CmdReturn();
    @Override
    public void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek) {
        stack.pop();
    }
}
