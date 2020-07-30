package com.zd.ctl.juc.task.cancel;

import java.util.concurrent.BlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.7-不可取消的任务在退出前恢复中断
 */
public class TaskInterruptDemo {


    public Task getNextTask(BlockingQueue<Task> queue){
        boolean interrupted = false;

        try {
            while (true){
                try {
                    return queue.take();
                } catch (InterruptedException e){
                    interrupted = true;
                    //重新尝试
                }
            }
        }finally {
            if (interrupted){
                Thread.currentThread().interrupt();
            }
        }
    }


    public class Task {}

}
