package com.zd.ctl.juc.task.exec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author ruyin_zh
 * @date 2020-07-28
 * @title
 * @description 6.8-支持关闭操作的web服务器
 */
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    public void start() throws IOException {

        ServerSocket ss = new ServerSocket(8080);

        while (!exec.isShutdown()){
            try {
                final Socket socket = ss.accept();
                exec.execute(() -> handleRequest(socket));
            }catch (RejectedExecutionException e){
                if (!exec.isShutdown()){
                    System.out.println("task submission rejected," + e.getMessage());
                }
            }

        }

    }

    public void stop(){
        exec.shutdown();
    }

    private void handleRequest(Socket socket){
        Request request = readRequest(socket);
        if (isShutdownRequest(request)){
            stop();
        }else {
            dispatchRequest(request);
        }
    }

    private boolean isShutdownRequest(Request request){

        return false;
    }

    private void dispatchRequest(Request request){

    }

    private Request readRequest(Socket socket){

        return null;
    }

    public class Request{}
}
