package com.zd.ctl.juc.thread.safety.atomic;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @title 竞态条件
 * @description 2.3-延迟初始化中的竞态条件
 * @eval bad
 */
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance(){
        if (instance == null){
            instance = new ExpensiveObject();
        }

        return instance;
    }

    public class ExpensiveObject{}
}
