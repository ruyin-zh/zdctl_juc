package com.zd.ctl.juc.obj.share.visibility;

/**
 * @author ruyin_zh
 * @date 2020-06-29
 * @title 失效数据
 * @description 3.2-非线程安全的可变整数类
 */
public class MutableInteger {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
