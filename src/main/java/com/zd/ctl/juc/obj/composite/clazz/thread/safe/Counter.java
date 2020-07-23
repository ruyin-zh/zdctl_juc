package com.zd.ctl.juc.obj.composite.clazz.thread.safe;

/**
 * @author ruyin_zh
 * @date 2020-07-03
 * @title 设计线程安全的类
 * @description 4.1-使用java监视器模式的线程安全计数器
 */
public class Counter {

    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment(){
        if (value == Long.MAX_VALUE){
            throw new IllegalStateException("counter overflow");
        }

        return ++value;
    }
}
