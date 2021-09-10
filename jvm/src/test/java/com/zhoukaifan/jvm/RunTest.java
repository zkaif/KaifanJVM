package com.zhoukaifan.jvm;

import com.zhoukaifan.jvm.cmd.ArgsController;
import com.zhoukaifan.jvm.cmd.CmdDTO;

public class RunTest {
    public static void main(String[] args){
        TestArgsController argsController = new TestArgsController();
        CmdDTO cmdDTO = argsController.handle(args);
        if (cmdDTO!=null) {
            javaMain(cmdDTO);
        }
    }
    public static void javaMain(CmdDTO cmdDTO){
        JVMContorller jvmContorller = new JVMContorller(cmdDTO);
        jvmContorller.run();
    }
}
