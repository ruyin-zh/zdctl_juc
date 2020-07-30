package com.zd.ctl.juc.task.cancel;

import com.zd.ctl.juc.blocks.Preloader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.10-通过Future来取消任务
 */
public class TaskInterruptByFuture {

    private static final ExecutorService taskExec = Executors.newFixedThreadPool(10);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit){

        Future<?> task = taskExec.submit(r);

        try {
            task.get(timeout,unit);
        } catch (InterruptedException e) {
            //接下来任务将被取消
        } catch (ExecutionException e) {
            //如果在任务中抛出了异常,那么重新抛出该异常
            throw Preloader.launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            //接下来任务将被取消
        }finally {
            //如果任务已经完成,那么取消也没有什么影响
            //如果任务正在执行,那么将会被中断
            task.cancel(true);
        }
    }

}
