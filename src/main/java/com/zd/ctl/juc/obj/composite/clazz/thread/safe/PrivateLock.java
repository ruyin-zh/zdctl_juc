package com.zd.ctl.juc.obj.composite.clazz.thread.safe;

/**
 * @author ruyin_zh
 * @date 2020-07-07
 * @title java监视器模式
 * @description 4.3-通过一个私有锁来保护状态
 */
public class PrivateLock {

    //遵循java监视器模式的对象会把对象的所有可变状态都封装起来,并由对象自己的内置锁来保护
    private final Object myLock = new Object();

    Widget widget;

    void someMethod(){
        synchronized (myLock){
            //修改或访问widget
        }

        /**synchronized (this){
            //修改或访问widget
        }*/
    }


    public class Widget {}
}
