package com.zd.ctl.juc.blocks;

import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-07-26
 * @title 构建高效且可伸缩的结果缓存
 * @description
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {


    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //耗时很长的计算任务
        return new BigInteger(arg);
    }
}
