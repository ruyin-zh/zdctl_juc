package com.zd.ctl.juc.obj.share.publish.escape;


import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title
 * @description 3.8-使用工厂方法来防止this引用在构造过程中逸出
 */
public class SafeListener {

    private final EventListener listener;


    /**
     *
     * 如果想在构造方法中注册一个事件监听器或启动线程,
     * 那么可以使用一个私有的构造方法和一个公共的工厂方法,
     * 从而避免不正确的构造过程
     *
     * */
    private SafeListener(){
        listener = new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                doSomething(evt);
            }
        };
    }


    public static SafeListener newInstance(EventTarget target){
        SafeListener safe = new SafeListener();
        target.addEventListener("ss",safe.listener,false);
        return safe;
    }


    private void doSomething(Event event){}
}
