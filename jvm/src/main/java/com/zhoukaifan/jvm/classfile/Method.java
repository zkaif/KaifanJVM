package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.constantimpl.ClassConstantUTF8;
import com.zhoukaifan.jvm.utils.MethodUtils;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class Method {
    private AccessFlagsMethod accessFlagsMethod;
    private ClassConstantUTF8 nameConstant;
    private ClassConstantUTF8 desConstant;
    private Attributes[] attributes;
    private Map<String,Attributes> attributesMap;
    private List<String> paramList;
    public static Method createByByteBuffer(ByteBuffer byteBuffer,ClassConstant[] classConstants) {
        Method method = new Method();
        short flag = byteBuffer.getShort();
        AccessFlagsMethod accessFlagsMethod = new AccessFlagsMethod(flag);
        method.accessFlagsMethod = accessFlagsMethod;
        short nameIndex = byteBuffer.getShort();
        method.nameConstant = (ClassConstantUTF8) classConstants[nameIndex];
        short desIndex = byteBuffer.getShort();
        method.desConstant = (ClassConstantUTF8) classConstants[desIndex];
        short attributesCount = byteBuffer.getShort();
        method.attributes = new Attributes[attributesCount];
        method.attributesMap = new ConcurrentHashMap<>();
        for (int i = 0;i<attributesCount;++i) {
            Attributes attributes = Attributes.createByByteBuffer(byteBuffer,classConstants);
            method.attributes[i] = attributes;
            method.attributesMap.put(attributes.getName(),attributes);
        }
        method.paramList = MethodUtils.parseDesc(method.desConstant.getContent());
        return method;
    }

    public int getParamCount() {
        return paramList.size();
    }

    public Class[] getNativeParamList() {
        Class[] classes = new Class[paramList.size()];
        for (int n = 0;n<paramList.size();++n){
            String param = paramList.get(n);
            Class aClass = MethodUtils.vmTypeToNativeClass(param);
            classes[n] = aClass;
        }
        return classes;
    }
}
