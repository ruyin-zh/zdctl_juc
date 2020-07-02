package com.zd.ctl.juc.obj.share.publish.escape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title
 * @description 3.7-隐式地使this引用逸出
 * @eval bad
 * @detail 不要在构造过程中使this引用逸出
 */
public class ThisEscape {


    /**
     *
     * 另外一种发布对象的形式就是发布一个内部的类实例,发布JDialog实例时也隐含地发布了ThisEscape实例自身
     *
     * */
    public ThisEscape(EventHandler handler){
        handler.create(ActionListener.class, new JDialog(){
            @Override
            public boolean action(Event evt, Object what) {
                return super.action(evt, what);
            }
        }, "show");
    }
}
