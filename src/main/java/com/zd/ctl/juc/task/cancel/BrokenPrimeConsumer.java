package com.zd.ctl.juc.task.cancel;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.3-不可靠的取消把生产者置于阻塞的操作中
 * @eval bad
 */
public class BrokenPrimeConsumer {

    private volatile boolean needMorePrimes = true;

    /**
     *
     * 存在的问题:在消费端主动取消消费数据之时并且生产者端消息已经堆积,
     * 生产者线程遭遇阻塞,此时若想变更生产者的状态则永远无法做到
     *
     *
     * */
    public void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>(10);
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(queue);
        primeProducer.start();

        try {
            while (needMorePrimes){
                consume(queue.take());
            }
        }finally {
            primeProducer.cancel();
        }
    }


    private void consume(BigInteger bi){

    }


    public void cancel(){
        this.needMorePrimes = false;
    }
}
