package com.zd.ctl.juc.task.cancel;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.3-不可靠的取消把生产者置于阻塞的操作中
 * @eval bad
 */
public class BrokenPrimeProducer extends Thread {


    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            try {
                p = p.nextProbablePrime();
                queue.put(p);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    protected void cancel(){
        this.cancelled = true;
    }

}
