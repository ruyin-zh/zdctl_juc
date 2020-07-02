package com.zd.concurrent.test;

/**
 * @author ruyin_zh
 * @date 2020-06-29
 * @title
 * @description 计算20000以内的奇数
 */
public class Calculator implements Runnable {


    @Override
    public void run() {
        long current = 1;
        long max = 20000;
        long numPrimes = 0;


        System.out.printf("Thread '%s' START\n",Thread.currentThread().getName());
        while (current <= max){
            if (isPrimes(current)){
                numPrimes++;
            }

            current++;
        }
        System.out.printf("Thread '%s' END, number of primes: %d\n",Thread.currentThread().getName(),numPrimes);
    }


    private boolean isPrimes(long num){
        return num % 2 != 0;
    }
}
