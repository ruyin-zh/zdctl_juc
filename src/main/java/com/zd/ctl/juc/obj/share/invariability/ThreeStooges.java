package com.zd.ctl.juc.obj.share.invariability;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title 不变性
 * @description 3.11-在可变对象基础上构建的不可变类
 */
public class ThreeStooges {

    /**
     *
     * 在不可变对象的内部仍可以使用可变对象来管理它们的状态
     *
     * */
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges(){
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }


    public boolean isStooges(String name){
        return stooges.contains(name);
    }
}
