package com.zhoukaifan.jvm.classfile;

public class AccessFlagsField {
    private short flags;

    public AccessFlagsField(short flags) {
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
}
