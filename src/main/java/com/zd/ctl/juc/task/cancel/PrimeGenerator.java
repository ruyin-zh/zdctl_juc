package com.zd.ctl.juc.task.cancel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 7.1-使用volatile保存取消状态
 */
public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;

        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }




    /**
     *
     * 让素数生成器运行一秒钟后取消,生成器没必要在一秒钟内严格取消,因为请求取消的时间与run循环检查之间可能存在延迟;
     *
     * */
    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }finally {
            generator.cancel();
        }

        return generator.get();
    }

    public static void main(String[] args) throws InterruptedException {
        List<BigInteger> list = aSecondOfPrimes();
        System.out.println(list);
    }
}
