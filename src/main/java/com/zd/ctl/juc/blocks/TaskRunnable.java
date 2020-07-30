package com.zd.ctl.juc.blocks;

import java.util.concurrent.BlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 5.10-恢复中断状态,避免掩盖中断
 */
public class TaskRunnable implements Runnable {

    private final BlockingQueue<Task> queue;

    public TaskRunnable(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            processTask(queue.take());
        }
        //进入此处表明线程已然发生中断,但是可能该线程的中断状态并未设置为中断状态
        catch (InterruptedException e){
            //恢复中断状态
            Thread.currentThread().interrupt();
        }
    }


    public class Task {

    }

    private void processTask(Task task){

    }
}
