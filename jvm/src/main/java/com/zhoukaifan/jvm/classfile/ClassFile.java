package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantClass;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantFieldref;
import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@ToString
@Slf4j
public class ClassFile {
    private int fileCode;
    private short minorVersion;
    private short mainVersion;
    private ClassConstant[] constants;
    private AccessFlagsClass accessFlagsClass;
    private ClassName className;
    private ClassName superClassName;
    private InterfaceName[] interfaceNames;
    private Field[] fields;
    private Method[] methods;
    private Map<String,Method> methodMap;
    public static ClassFile createClassFile(ByteBuffer byteBuffer){
        ClassFile classFile = new ClassFile();
        byteBuffer.position(0);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        classFile.fileCode = byteBuffer.getInt();
        if (-889275714!=classFile.fileCode){
            return null;
        }
        classFile.minorVersion = byteBuffer.getShort();
        classFile.mainVersion = byteBuffer.getShort();
        log.debug("fileCode:0xCAFEBABE    minorVersion:{}    mainVersion:{}",classFile.minorVersion,classFile.mainVersion);
        short constantsLength = byteBuffer.getShort();
        classFile.constants = new ClassConstant[constantsLength];
        classFile.constants[0] = ClassConstant.UNDEFINED;
        for (int i = 1;i<classFile.constants.length;++i){
            byte type = byteBuffer.get();
            ClassConstant classConstant = ClassConstant.newBuilder(type).buildByByteBuffer(byteBuffer);
            classFile.constants[i] = classConstant;
        }
        short accessFlags = byteBuffer.getShort();
        classFile.accessFlagsClass = new AccessFlagsClass(accessFlags);

        short classNameIndex = byteBuffer.getShort();
        ClassConstantClass classConstantClass = (ClassConstantClass) classFile.constants[classNameIndex];
        ClassConstantUTF8 classConstantUTF8 = (ClassConstantUTF8) classFile.constants[classConstantClass.getStringIndex()];
        ClassName className = new ClassName(classNameIndex,classConstantClass, classConstantUTF8);
        classFile.className = className;

        short superClassNameIndex = byteBuffer.getShort();
        ClassConstantClass superClassConstantClass = (ClassConstantClass) classFile.constants[superClassNameIndex];
        ClassConstantUTF8 superClassConstantUTF8 = (ClassConstantUTF8) classFile.constants[superClassConstantClass.getStringIndex()];
        ClassName superClassName = new ClassName(superClassNameIndex,superClassConstantClass, superClassConstantUTF8);
        classFile.superClassName = superClassName;

        short interfaceNamesIndex = byteBuffer.getShort();
        classFile.interfaceNames = new InterfaceName[interfaceNamesIndex];
        for (int i = 0;i<interfaceNamesIndex;++i){
            InterfaceName interfaceName = InterfaceName.createByByteBuffer(byteBuffer,classFile.constants);
            classFile.interfaceNames[i] = interfaceName;
        }

        short fieldIndex = byteBuffer.getShort();
        classFile.fields = new Field[fieldIndex];
        int bytesIndex = 0;
        for (int i = 0;i<fieldIndex;++i){
            Field field = Field.createByByteBuffer(byteBuffer,classFile.constants,bytesIndex);
            classFile.fields[i] = field;
            bytesIndex+=field.getSizeForBytes();
        }

        short methodIndex = byteBuffer.getShort();
        classFile.methods = new Method[methodIndex];
        classFile.methodMap = new ConcurrentHashMap<>();
        for (int i = 0;i<methodIndex;++i){
            Method method = Method.createByByteBuffer(byteBuffer,classFile.constants);
            classFile.methods[i] = method;
            classFile.methodMap.put(method.getNameConstant().getContent()+":"+method.getDesConstant().getContent(),method);
        }

        return classFile;
    }

    public int getFieldSizeForBytes(){
        int size = 0;
        for (Field field:fields){
            size += field.getSizeForBytes();
        }
        return size;
    }

    public Field getFieldByName(ClassConstantUTF8 classConstantUTF8) {
        for (Field field : fields){
            if (classConstantUTF8.getContent().equals(field.getNameConstant().getContent())){
                return field;
            }
        }
        return null;
    }
}
