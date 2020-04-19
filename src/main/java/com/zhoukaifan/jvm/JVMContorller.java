package com.zhoukaifan.jvm;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classfile.Method;
import com.zhoukaifan.jvm.classpath.IClassPath;
import com.zhoukaifan.jvm.classpath.impl.ClassPathControl;
import com.zhoukaifan.jvm.cmd.CmdDTO;
import com.zhoukaifan.jvm.engine.ICodeEngine;
import com.zhoukaifan.jvm.engine.codeimpl.CodeEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JVMContorller {
    private CmdDTO cmdDTO;
    private IClassPath classPath;
    private ClassFile mainClass;
    private Method mainMethod;
    private ICodeEngine codeEngine;

    public JVMContorller(CmdDTO cmdDTO) {
        this.cmdDTO = cmdDTO;
        classPath = new ClassPathControl(cmdDTO.getClasspath());
        try {
            ClassFile classFile = classPath.getClassFileByClassName(cmdDTO.getRun());
            mainClass = classFile;
            mainMethod = classFile.getMethodMap().get("main:([Ljava/lang/String;)V");
        } catch (IOException e) {
            log.error("JVMContorller--IOException", e);
        }
        codeEngine = new CodeEngine();
    }

    public void run() {
        codeEngine.befor(mainClass, mainMethod, Thread.currentThread().getId() + "");
        codeEngine.runCode();
    }
}
