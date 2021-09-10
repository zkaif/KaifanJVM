package com.zhoukaifan.jvm.classfile.constantimpl;

import com.zhoukaifan.jvm.classfile.ClassConstant;
import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classpath.impl.ClassPathControl;
import com.zhoukaifan.jvm.memory.RunTimeData;
import com.zhoukaifan.jvm.obj.ObjInstance;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassConstantString extends ClassConstant {

    private short stringIndex;
    private long stringObjIndex;

    public ClassConstantString(short stringIndex) {
        super((byte) 8);
        this.stringIndex = stringIndex;
    }

    public long getStringObj(ClassConstantUTF8 classConstantUTF8) {
        ObjInstance objInstance = RunTimeData.getInstance().getSharedData().getMethod().getStaticData(stringObjIndex);
        if (objInstance==null){
            byte[] utf8Bytes = classConstantUTF8.getContent().getBytes();
            ClassFile classFileByClassName = ClassPathControl.getInstance().getClassFileByClassName("java/lang/String");
            ObjInstance bytes = new ObjInstance(true, utf8Bytes.length, "B");
            bytes.copy(utf8Bytes);
            long bytesObjIndex = RunTimeData.getInstance().getSharedData().getMethod().putStaticData(bytes);
            objInstance = new ObjInstance(classFileByClassName);
            objInstance.getByteBuffer().putLong(bytesObjIndex);
            stringObjIndex = RunTimeData.getInstance().getSharedData().getMethod().putStaticData(objInstance);
        }
        return stringObjIndex;
    }
}
