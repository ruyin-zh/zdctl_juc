package com.zd.ctl.juc.blocks;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xzwang
 * @date 2020-07-26
 * @title 构建高效且可伸缩的结果缓存
 * @description 5.16-使用HashMap和同步机制来初始化缓存
 */
public class Memoizer1<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }


    /**
     *
     * 存在的问题:使用synchronized关键字使得程序的吞吐量及响应延迟大大增加
     *
     *
     * */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }

        return result;
    }
}
