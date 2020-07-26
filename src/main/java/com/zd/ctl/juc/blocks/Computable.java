package com.zd.ctl.juc.blocks;

/**
 * @author ruyin_zh
 * @date 2020-07-26
 * @title 构建高效且可伸缩的结果缓存
 * @description
 */
public interface Computable<A,V> {

    V compute(A arg) throws InterruptedException;

}
