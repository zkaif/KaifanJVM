package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Stack;

public abstract class CmdAloadAbs implements ICommand {
    public void runAload(Stack.Frame peek,int index) {
        Object o = peek.getLocalVariable().get(index);
        peek.getOperandStack().push(o);
    }
}
