package com.zd.ctl.juc.task.cancel;

import java.net.Socket;

/**
 * @author ruyin_zh
 * @date 2020-07-30
 * @title
 * @description
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }



}
