package com.zhoukaifan.jvm;

import com.zhoukaifan.jvm.cmd.ArgsController;
import com.zhoukaifan.jvm.cmd.CmdDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Java {
    public static void main(String[] args){
        ArgsController argsController = new ArgsController();
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
