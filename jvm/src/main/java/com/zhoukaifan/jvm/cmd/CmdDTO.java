package com.zhoukaifan.jvm.cmd;

import lombok.Data;

@Data
public class CmdDTO {
    private String[] classpath;
    private String run;
    private String[] args;
}
