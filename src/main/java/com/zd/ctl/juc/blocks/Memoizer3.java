package com.zd.ctl.juc.blocks;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author ruyin_zh
 * @date 2020-07-26
 * @title 构建高效且可伸缩的结果缓存
 * @description 5.18-基于FutureTask的Memoizing封装器
 */
public class Memoizer3<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    /**
     *
     * 该版本已经趋于完善,在吞吐量上及延时上均提升不少
     * 存在的问题: 此处f==null在并发情况下会存在多条线程进入,从而导致cache.put()操作仍存在两个线程都执行了了相同计算的问题;
     * 另外此处未针对任务取消情况做处理,未处理任务取消的场景而只是打印了异常
     *
     * */
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null){
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);

            f = ft;
            //菲原子操作
            cache.put(arg,ft);
            ft.run();
        }

        try {
            return f.get();
        } catch (ExecutionException e) {
            throw Preloader.launderThrowable(e.getCause());
        }
    }
}
