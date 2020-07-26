package com.zd.ctl.juc.blocks;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author ruyin_zh
 * @date 2020-07-27
 * @title 构建高效且可伸缩的结果缓存
 * @description 5.19-Memoizer最终实现
 */
public class Memoizer<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null){
            Callable<V> eval = () -> c.compute(arg);

            FutureTask<V> ft = new FutureTask<>(eval);

            f = cache.putIfAbsent(arg,ft);
            if (f == null){
                f = ft;
                ft.run();
            }
        }

        try {
            return f.get();
        } catch (CancellationException e){
            cache.remove(arg,f);
            throw Preloader.launderThrowable(e.getCause());
        } catch (ExecutionException e) {
            throw Preloader.launderThrowable(e.getCause());
        }
    }
}
