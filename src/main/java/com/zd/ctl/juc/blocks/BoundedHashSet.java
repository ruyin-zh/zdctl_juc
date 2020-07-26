package com.zd.ctl.juc.blocks;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author ruyin_zh
 * @date 2020-07-24
 * @title
 * @description 5.14-使用信号量约束容器
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore sm;

    public BoundedHashSet(int bound){
        set = Collections.synchronizedSet(new HashSet<>());
        sm = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        sm.acquire();
        boolean added = false;

        try {
            added = set.add(t);
            return added;
        }finally {
            if (!added){
                sm.release();
            }
        }
    }

    public boolean remove(T t){
        boolean removed = set.remove(t);
        if (removed){
            sm.release();
        }

        return removed;
    }
}
