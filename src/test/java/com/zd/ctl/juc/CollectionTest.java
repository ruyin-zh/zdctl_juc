package com.zd.ctl.juc;

import com.zd.ctl.juc.obj.composite.clazz.thread.safe.Counter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
        ExecutorService executor = Executors.newFixedThreadPool(4);
        SynchronousQueue<String> sq = new SynchronousQueue<>();

        Runnable producer = () -> {
            String str = "test";
            try {
                sq.put(str);
                System.out.println("po:" + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable producer1 = () -> {
            String str = "prod";
            try {
                sq.put(str);
                System.out.println("po:" + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        Runnable consumer = () -> {
            String o = null;
            try {
                o = sq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("co:" + o);
        };

        //顺序对于执行存在影响
        executor.submit(consumer);
        //executor.submit(producer);
        executor.submit(consumer);
        executor.submit(producer);
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.submit(producer1);

        //executor.awaitTermination(200, TimeUnit.SECONDS);
        executor.shutdown();
    }


    @Test
    @DisplayName("测试SynchronizedQueue同步")
    public void testSynchronizedQueue1() throws InterruptedException {
        SynchronousQueue<String> sq = new SynchronousQueue<>(false);

        boolean os = sq.offer("abc");
        String ps = sq.poll();

        System.out.println(os + ":" + ps);
    }


    @Test
    public void testCtlCount(){
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);

        System.out.println(CAPACITY);
        System.out.println(workerCountOf(ctl.get()));

    }


    @Test
    public void test1(){
        try {
            Unsafe UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = Counter.class;
            long value = UNSAFE.objectFieldOffset(k.getDeclaredField("value"));

            System.out.println(value);
        } catch (Exception e) {
            throw new Error(e);
        }
    }


    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
}
