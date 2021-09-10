package com.zhoukaifan.jvm;

import com.zhoukaifan.jvm.cmd.CmdDTO;

public class TestArgsController {
    public CmdDTO handle(String[] args) {
        String prefix = "/Users/dim/IdeaProjects/KaifanJVM";
        CmdDTO cmdDTO = new CmdDTO();
        cmdDTO.setArgs(new String[0]);
        cmdDTO.setClasspath(new String[]{prefix+"/test/target/classes/",prefix+"/jdk/target/classes/"});
        cmdDTO.setRun("com/zhoukaifan/test/Test");
        return cmdDTO;
    }
}
