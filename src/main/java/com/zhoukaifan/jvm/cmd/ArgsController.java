package com.zhoukaifan.jvm.cmd;

public class ArgsController {
    public CmdDTO handle(String[] args) {
        CmdDTO cmdDTO = new CmdDTO();
        cmdDTO.setArgs(new String[0]);
        cmdDTO.setClasspath(new String[]{"/Users/dim/IdeaProjects/KaifanJVM/src/main/resources/Test.jar"});
        cmdDTO.setRun("com/company/Main");
        return cmdDTO;
    }
}
