package com.zd.ctl.juc.task.exec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ruyin_zh
 * @date 2020-07-27
 * @title
 * @description 6.4-使用线程池的WebServer
 */
public class TaskExecutionWebServer {


    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
        while (true){
            Socket socket = ss.accept();
            Runnable task = () -> SingleThreadWebServer.handleRequest(socket);
            exec.execute(task);
        }
    }

}
