package com.zhoukaifan.jvm.cmd;

public class ArgsController {
    public CmdDTO handle(String[] args) {
        CmdDTO cmdDTO = new CmdDTO();
        cmdDTO.setArgs(new String[0]);
        cmdDTO.setClasspath(new String[]{"/Users/dim/IdeaProjects/KaifanJVM/test/target/classes/","/Users/dim/IdeaProjects/KaifanJVM/jdk/target/classes/"});
        cmdDTO.setRun("com/zhoukaifan/test/Test");
        return cmdDTO;
    }
}
