package com.zd.ctl.juc.blocks;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author ruyin_zh
 * @date 2020-07-22
 * @title
 * @description 5.6-迭代隐藏在字符串拼接过程中
 * @eval bad
 */
public class HiddenIterator implements Runnable {

    private CountDownLatch cdl;

    public HiddenIterator(CountDownLatch cdl){
        this.cdl = cdl;
    }

    private final Set<Integer> set = new HashSet<>();

    public synchronized boolean add(int i){
        return set.add(i);
    }

    public synchronized boolean remove(int i){
        return set.remove(i);
    }

    @Override
    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random r = new Random();
        for (int i = 0; i < 10; i++){
            add(r.nextInt());
        }

        System.out.println(Thread.currentThread().getName() + ",total ele:" + set);
    }

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        HiddenIterator iterator = new HiddenIterator(cdl);
        for (int i = 0; i < 5; i++){
            new Thread(iterator).start();
        }

        cdl.countDown();
    }
}
