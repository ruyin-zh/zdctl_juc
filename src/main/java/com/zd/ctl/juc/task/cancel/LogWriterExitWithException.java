package com.zd.ctl.juc.task.cancel;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-30
 * @title
 * @description 7.14-向日志服务添加不可靠的关闭支持
 */
public class LogWriterExitWithException {

    private static final int CAPACITY = 256;

    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    private volatile boolean shutdownRequested = false;

    public LogWriterExitWithException(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start(){
        new Thread(logger).start();
    }

    public void log(String message) throws InterruptedException {
        if (!shutdownRequested){
            queue.put(message);
        }else {
            throw new IllegalStateException("logger is shutdown");
        }
    }

    private class LoggerThread implements Runnable {

        private final Writer writer;

        public LoggerThread(Writer writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                while (true){
                    writer.write(queue.take());
                }
            }catch (InterruptedException e){

            }catch (IOException e){

            }finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
