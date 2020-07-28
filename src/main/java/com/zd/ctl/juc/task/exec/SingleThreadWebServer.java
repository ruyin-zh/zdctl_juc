package com.zd.ctl.juc.task.exec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ruyin_zh
 * @date 2020-07-27
 * @title
 * @description 6.1-顺序化的WebServer
 */
public class SingleThreadWebServer {

    /**
     *
     * 存在的问题: 一次只能处理一个请求,故在实际环境当中执行效率会很糟糕
     *
     *
     * */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
        while (true){
           Socket socket = ss.accept();
           handleRequest(socket);
        }
    }

    public static void handleRequest(Socket socket){

    }
}
