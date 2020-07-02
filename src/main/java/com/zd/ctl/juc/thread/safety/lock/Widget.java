package com.zd.ctl.juc.thread.safety.lock;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @title 重入
 * @description 2.7-若内置锁不可重入,该段代码将发生死锁
 */
public class Widget {

    public synchronized void doSomething(){
        System.out.println("top doSomething");
    }


    public class LoggingWidget extends Widget {

        @Override
        public synchronized void doSomething() {
            System.out.println("sub doSomething");
            super.doSomething();
        }
    }
}
