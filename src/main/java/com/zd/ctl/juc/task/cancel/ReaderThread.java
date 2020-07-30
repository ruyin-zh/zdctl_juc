package com.zd.ctl.juc.task.cancel;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title 处理不可中断的阻塞
 * @description 7.11-通过改写interrupt方法将非标准的取消操作封装在Thread中
 */
public class ReaderThread extends Thread {

    private static final int BUFFSZ = 1024;

    private final Socket socket;
    private final InputStream is;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.is = socket.getInputStream();
    }

    //改写interrupt方法
    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e){
          //忽略socket关闭的异常
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFFSZ];
            while (true){
                int count = is.read(buf);
                if (count < 0){
                    break;
                }else {
                    processBuffer(buf,count);
                }
            }
        }catch (IOException e){
            //允许线程退出
        }
    }

    private void processBuffer(byte[] buf, int count){

    }
}
