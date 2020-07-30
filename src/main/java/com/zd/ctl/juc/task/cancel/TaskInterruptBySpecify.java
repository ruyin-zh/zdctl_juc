package com.zd.ctl.juc.task.cancel;

import com.zd.ctl.juc.blocks.Preloader;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.9-在专门的线程中中断任务
 */
public class TaskInterruptBySpecify {


    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(10);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {


        class RethrowableTask implements Runnable {

            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                }catch (Throwable t){
                    this.t = t;
                }
            }

            void rethrow(){
                if (t != null){
                    throw Preloader.launderThrowable(t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(() -> taskThread.interrupt(),timeout,unit);
        //无法知道执行控制是因为线程正常退出而返回还是因为join超时而返回
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

}
