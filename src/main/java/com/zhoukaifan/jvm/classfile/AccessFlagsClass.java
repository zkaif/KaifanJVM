package com.zhoukaifan.jvm.classfile;

public class AccessFlagsClass {
    private short flags;

    public AccessFlagsClass(short flags) {
        this.flags = flags;
    }

    public boolean isPublic(){
        return (flags&0x0001)==0x0001;
    }
    public boolean isFinal(){
        return (flags&0x0010)==0x0010;
    }
    public boolean isSuper(){
        return (flags&0x0020)==0x0020;
    }
    public boolean isInterface(){
        return (flags&0x0200)==0x0200;
    }
    public boolean isAbstract(){
        return (flags&0x0400)==0x0400;
    }
    public boolean isSynthetic(){
        return (flags&0x1000)==0x1000;
    }
    public boolean isAnnotation(){
        return (flags&0x2000)==0x2000;
    }
    public boolean isEnum(){
        return (flags&0x4000)==0x4000;
    }
    public boolean isModule(){
        return (flags&0x8000)==0x8000;
    }
}
