package com.zd.ctl.juc.obj.share.visibility;

/**
 * @author ruyin_zh
 * @date 2020-06-29
 * @title 失效数据
 * @description 3.3-线程安全的可变整数类
 */
public class SynchronizedInteger {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
