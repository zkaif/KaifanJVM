package com.zhoukaifan.jvm.memory;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class RunTimeData {
    private SharedData sharedData;
    private Map<String,ThreadData> threadIdAndDataMap;

    private static RunTimeData runTimeData;
    static{

        runTimeData = new RunTimeData();
        runTimeData.sharedData = new SharedData();
        runTimeData.threadIdAndDataMap = new ConcurrentHashMap<String,ThreadData>();

    }
    public static RunTimeData getInstance(){
        return runTimeData;
    }
    public ThreadData removeById(String id){
        return threadIdAndDataMap.remove(id);
    }
    public ThreadData getById(String id){
        return threadIdAndDataMap.get(id);
    }
    public ThreadData getByIdOrNew(String id){
        ThreadData threadData = threadIdAndDataMap.get(id);
        if (threadData==null){
            synchronized (this){
                threadData = threadIdAndDataMap.get(id);
                if (threadData==null){
                    threadData = new ThreadData();
                    threadIdAndDataMap.put(id,threadData);
                }
            }
        }
        return threadData;
    }
}
