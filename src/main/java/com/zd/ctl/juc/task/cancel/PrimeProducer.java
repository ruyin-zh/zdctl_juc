package com.zd.ctl.juc.task.cancel;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.5-通过使用中断进行取消(而非状态位)
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger bi = BigInteger.ONE;

        while (!Thread.currentThread().isInterrupted()){
            try {
                bi = bi.nextProbablePrime();
                queue.put(bi);
            }catch (InterruptedException e){
                //允许线程退出
            }
        }
    }

    public void cancel(){
        Thread.currentThread().interrupt();
    }
}
