package com.zd.ctl.juc.obj.share.publish.escape;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title
 * @description 3.6-使内部的可变状态逸出
 * @eval bad
 */
public class UnsageState {

    private String[] states = new String[]{"AK","AL","AJ"};


    /**
     *
     * 若以此方法发布states就会出现问题,因为任何调用者都能修改这个数组的内容
     *
     * */
    public String[] getStates(){
        return states;
    }

}
