package com.zhoukaifan.jvm.classpath.impl;

import com.zhoukaifan.jvm.classfile.ClassFile;
import com.zhoukaifan.jvm.classpath.IClassPath;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ClassPathControl implements IClassPath {
    private static ClassPathControl CLASS_PATH_CONTROL;
    public static ClassPathControl getInstance(){
        return CLASS_PATH_CONTROL;
    }

    private List<IClassPath> classPaths;
    private Map<String, ClassFile> classFileMap = new ConcurrentHashMap<>();
    private static final ClassFile NULL_OBJECT = new ClassFile();

    public ClassPathControl(String[] classPathArray) {
        classPaths = new ArrayList<>();
        for (String classPathStr:classPathArray) {
            IClassPath classPath = null;
            if (classPathStr.endsWith(".jar")) {
                classPath = ClassPathJar.getClassPathJar(classPathStr);
            }else {
                classPath = ClassPathDirectory.getClassPathDirectory(new File(classPathStr));
            }
            classPaths.add(classPath);
        }
        CLASS_PATH_CONTROL = this;
    }

    @Override
    public ClassFile getClassFileByClassName(String className) {
        ClassFile classFileByClassName = classFileMap.get(className);
        if (classFileByClassName==null) {
            synchronized (this) {
                classFileByClassName = classFileMap.get(className);
                if (classFileByClassName==null) {
                    Iterator<IClassPath> iterator = classPaths.iterator();
                    while (iterator.hasNext()) {
                        IClassPath classPath = iterator.next();
                        try {
                            classFileByClassName = classPath.getClassFileByClassName(className);
                        } catch (IOException e) {
                            log.error("IOException:",e);
                            return null;
                        }
                        if (classFileByClassName != null) {
                            classFileMap.put(className,classFileByClassName);
                            return classFileByClassName;
                        }
                    }
                    classFileMap.put(className,NULL_OBJECT);
                    return null;
                }
            }
        }
        return classFileByClassName;
    }
}
