package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;

public class CmdAload0 extends CmdAloadAbs implements ICommand {
    public static ICommand CMD = new CmdAload0();
    @Override
    public void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek) {
        runAload(peek,0);
    }
}
