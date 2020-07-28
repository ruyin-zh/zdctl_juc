package com.zd.ctl.juc.task.exec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ruyin_zh
 * @date 2020-07-27
 * @title
 * @description 6.2-每个线程对应一个请求或task
 */
public class ThreadPerTaskWebServer {

    /**
     *
     * 存在的问题: 为每个请求单独创建一个线程存在的弊端:
     *              1)、线程生命周期的开销;
     *              2)、资源消耗量;
     *              3)、稳定性;不同平台的差异、JVM参数、Thread构造函数请求栈大小
     *
     * */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
        while (true){
            Socket socket = ss.accept();
            Runnable task = () -> SingleThreadWebServer.handleRequest(socket);

            new Thread(task).start();
        }
    }
}
