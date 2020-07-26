package com.zd.ctl.juc.blocks;

import java.util.concurrent.CountDownLatch;

/**
 * @author ruyin_zh
 * @date 2020-07-24
 * @title
 * @description 5.11-时序测试中使用CountDownLatch来启动和停止线程
 */
public class TestHarness {

    public static long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i ++){
            new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    }finally {
                        endGate.countDown();
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }


    public static void main(String[] args) {
        while (true){
            try {
                long time = timeTasks(3,() -> System.out.println(System.nanoTime()));
                System.out.println(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
