package com.zhoukaifan.jvm.cmd;

public class ArgsController {
    public CmdDTO handle(String[] args) {
        String prefixion = "/Users/dim/IdeaProjects/KaifanJVM";
        CmdDTO cmdDTO = new CmdDTO();
        cmdDTO.setArgs(new String[0]);
        cmdDTO.setClasspath(new String[]{prefixion+"/test/target/classes/",prefixion+"/jdk/target/classes/"});
        cmdDTO.setRun("com/zhoukaifan/test/Test");
        return cmdDTO;
    }
}
