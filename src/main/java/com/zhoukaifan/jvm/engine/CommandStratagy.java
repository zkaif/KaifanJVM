package com.zhoukaifan.jvm.engine;

import com.zhoukaifan.jvm.engine.commandimpl.CmdInvokestatic;
import com.zhoukaifan.jvm.engine.commandimpl.CmdLdc;
import com.zhoukaifan.jvm.engine.commandimpl.CmdReturn;

public class CommandStratagy {
    public static ICommand getByCommandCode(byte commandCode){
        switch (commandCode){
            case -79:
                return CmdReturn.CMD;
            case -72:
                return CmdInvokestatic.CMD;
            case 18:
                return CmdLdc.CMD;
            default:
                return null;
        }
    }
}
