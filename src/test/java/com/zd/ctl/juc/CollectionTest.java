package com.zd.ctl.juc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ruyin_zh
 * @date 2020-07-22
 * @title
 * @description
 */
public class CollectionTest {

    @Test
    @DisplayName("测试CopyOnWriteArrayList")
    public void testCopyOnWriteArrayList(){
        List cowList = new CopyOnWriteArrayList(Arrays.asList("a"));

    }


    @Test
    @DisplayName("测试ToArray方法")
    public void testToArray(){
        List list = Arrays.asList("a");
        Object[] objs = list.toArray();

        //java.lang.ArrayStoreException
        //实际类型为String,Object对象不可向下转型为String
        objs[0] = new Object();
        //java.lang.ClassCastException,java.lang.Object cannot be cast to java.lang.String
        objs[0] = (String) new Object();
    }


    @Test
    @DisplayName("测试同步")
    public void testSynchronizedWithCdl() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch cdl = new CountDownLatch(1);
        AtomicReference<Object> ar = new AtomicReference<>(new Object());

        Runnable producer = () -> {
            Object o = new Object();
            ar.set(o);
            System.out.println("po:" + o);
            //cdl.countDown();
        };

        Runnable consumer = () -> {
            try {
                //cdl.await();
                Object o = ar.get();
                System.out.println("co:" + o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        executor.submit(consumer);
        executor.submit(producer);

        executor.shutdown();
    }

    @Test
    @DisplayName("测试SynchronizedQueue同步")
    public void testSynchronizedQueue() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        SynchronousQueue<String> sq = new SynchronousQueue<>();

        Runnable producer = () -> {
            String str = "test";
            boolean success = sq.offer(str);
            System.out.println("po:" + str + ", success:" + success);
        };

        Runnable consumer = () -> {
            Object o = sq.poll();
            System.out.println("co:" + o);
        };

        //顺序对于执行存在影响
        //executor.submit(producer);
        //executor.submit(producer);
        //executor.submit(producer);
        executor.submit(consumer);
        executor.submit(consumer);

        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }


    @Test
    @DisplayName("测试SynchronizedQueue同步")
    public void testSynchronizedQueue1() throws InterruptedException {
        //ExecutorService executor = Executors.newFixedThreadPool(1);
        SynchronousQueue<String> sq = new SynchronousQueue<>();

//        Runnable producer = () -> {
//            String str = "test";
//            boolean success = sq.offer(str);
//            System.out.println("po:" + str + ", success:" + success);
//        };

//        Runnable consumer = () -> {
//            Object o = sq.poll();
//            System.out.println("co:" + o);
//        };

        //顺序对于执行存在影响
        //executor.submit(producer);
        //executor.submit(producer);
        //executor.submit(producer);
        //executor.submit(consumer);
        //executor.submit(consumer);

        //executor.awaitTermination(10, TimeUnit.SECONDS);
        //executor.shutdown();


        boolean os = sq.offer("abc");
        String ps = sq.poll();

        System.out.println(os + ":" + ps);

        os = sq.offer("bcd");
        ps = sq.poll();

        System.out.println(os + ":" + ps);

        os = sq.offer("cde");
        ps = sq.poll();

        System.out.println(os + ":" + ps);


        ps = sq.poll();
        os = sq.offer("def");

        System.out.println(os + ":" + ps);
    }
}
