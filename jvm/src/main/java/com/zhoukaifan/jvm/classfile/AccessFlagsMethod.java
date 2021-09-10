package com.zhoukaifan.jvm.classfile;

public class AccessFlagsMethod {
    private short flags;

    public AccessFlagsMethod(short flags) {
        this.flags = flags;
    }


    public boolean isPublic(){
        return (flags&0x0001)==0x0001;
    }
    public boolean isPrivate(){
        return (flags&0x0002)==0x0002;
    }
    public boolean isProtected(){
        return (flags&0x0004)==0x0004;
    }
    public boolean isStatic(){
        return (flags&0x0008)==0x0008;
    }
    public boolean isFinal(){
        return (flags&0x0010)==0x0010;
    }
    public boolean isSynchronized(){
        return (flags&0x0020)==0x0020;
    }
    public boolean isBridge(){
        return (flags&0x0040)==0x0040;
    }
    public boolean isVarargs(){
        return (flags&0x0080)==0x0080;
    }
    public boolean isNative(){
        return (flags&0x0100)==0x0100;
    }
    public boolean isAbstract(){
        return (flags&0x0400)==0x0400;
    }
    public boolean isStrict(){
        return (flags&0x0800)==0x0800;
    }
    public boolean isSynthetic(){
        return (flags&0x1000)==0x1000;
    }
}
