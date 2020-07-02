package com.zd.ctl.juc.obj.share.invariability;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title 不可变--使用volatile类型来发布不可变对象
 * @description 3.12-对数值及其因数分解结果进行缓存的不可变容器类
 * @detail 对于在访问和更新多个相关变量时出现的竞争条件问题,
 *         可以通过将这些变量全部保存在一个不可变对象中来消除
 *
 */
public class OneValueCache {

    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
    }

    public BigInteger[] getFactors(BigInteger bi){
        if (lastNumber == null || !lastNumber.equals(bi)){
            return null;
        }else {
            return Arrays.copyOf(lastFactors,lastFactors.length);
        }
    }
}
