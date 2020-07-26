package com.zd.ctl.juc.blocks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ruyin_zh
 * @date 2020-07-26
 * @title 构建高效且可伸缩的结果缓存
 * @description 5.17-使用ConcurrentHashMap替换HashMap
 */
public class Memoizer2<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    /**
     *
     * 存在的问题: 若一个线程启动一个开销很大的计算,而其它线程并不知道该计算正在运行,会导致重复计算该步骤
     *
     * */
    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }

        return result;
    }
}
