package com.zhoukaifan.jvm.engine;

import com.zhoukaifan.jvm.engine.commandimpl.*;

public class CommandStratagy {
    public static ICommand getByCommandCode(byte commandCode){
        switch (commandCode){
            case -79:
                return CmdReturn.CMD;
            case -72:
                return CmdInvokestatic.CMD;
            case -74:
                return CmdInvokevirtual.CMD;
            case 18:
                return CmdLdc.CMD;
            case 42:
                return CmdAload0.CMD;
            case -76:
                return CmdGetfield.CMD;
            case -66:
                return CmdArrayLength.CMD;
            case -84:
                return CmdIReturn.CMD;
            case -80:
                return CmdAReturn.CMD;
            default:
                return null;
        }
    }
}
