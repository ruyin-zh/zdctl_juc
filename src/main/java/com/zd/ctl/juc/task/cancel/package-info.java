/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description
 */
package com.zd.ctl.juc.task.cancel;

/**
 *
 * 任务取消
 *      休眠线程再次被调度的三个时机:1、针对当前休眠线程调用LockSupport.unpark()方法;
 *                               2、其它线程中断了当前线程;
 *                               3、操作系统底层原因导致的虚假唤醒,也就是await()操作不一定由notify()唤醒;
 *
 * 中断: 中断并不会真正中断一个正在运行的线程,它仅仅发出中断请求,线程会在一个方便的时间点自行中断(称之为取消点--cancellation point)
 *      静态的interrupted应该小心使用,因为它会清除并发线程的中断状态;
 *      若调用了interrupted并且返回了true,则在程序上必须对其进行处理,除非希望掩盖这个中断--可以通过抛出InterruptedException或通过再次调用interrupt来保存中断状态;
 *
 *      如此也解释了常用的线程取消机制与可阻塞的库函数无法良好互动的问题;
 *
 * 线程中断的处理策略
 * 1、传递异常,比如方法上抛出异常;
 * 2、保存中断状态,上层调用栈中的代码能够对其进行处理;
 *
 * 不可中断的阻塞
 * 1、java.io包中的同步Socket i/o
 * 2、java.io包中的同步i/o
 * 3、Selector的异步i/o
 * 4、获取某个锁
 *
 *  RunnableFuture
 *
 * */