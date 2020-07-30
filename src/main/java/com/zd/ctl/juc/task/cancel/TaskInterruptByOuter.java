package com.zd.ctl.juc.task.cancel;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.8-在外部线程中安排中断
 * @eval bad
 */
public class TaskInterruptByOuter {

    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(10);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit){

        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(() -> taskThread.interrupt(),timeout,unit);
        r.run();
    }

}
