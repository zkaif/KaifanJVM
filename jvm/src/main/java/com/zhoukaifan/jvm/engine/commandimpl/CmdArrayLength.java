package com.zhoukaifan.jvm.engine.commandimpl;

import com.zhoukaifan.jvm.engine.ICommand;
import com.zhoukaifan.jvm.memory.Counter;
import com.zhoukaifan.jvm.memory.RunTimeData;
import com.zhoukaifan.jvm.memory.Stack;
import com.zhoukaifan.jvm.memory.ThreadData;
import com.zhoukaifan.jvm.obj.ObjInstance;

public class CmdArrayLength implements ICommand {
    public static ICommand CMD = new CmdArrayLength();
    @Override
    public void run(ThreadData threadData, Counter counter, Stack stack, Stack.Frame peek) {
        long arrayIndex = (long) peek.getOperandStack().pop();
        ObjInstance objInstance = RunTimeData.getInstance().getSharedData().getBytesByIndex(arrayIndex);
        int arrayLength = objInstance.getArrayLength();
        peek.getOperandStack().push(arrayLength);
    }
}
