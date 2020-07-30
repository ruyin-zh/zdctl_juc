package com.zd.ctl.juc.task.cancel;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-30
 * @title
 * @description 7.13-不支持关闭的生产者-消费者日志服务
 */
public class LogWriter {

    private static final int CAPACITY = 1024;

    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    public LogWriter(Writer writer){
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start(){
        logger.start();
    }

    public void log(String message) throws InterruptedException {
        queue.put(message);
    }

    private class LoggerThread extends Thread {

        private final Writer writer;

        public LoggerThread(Writer writer){
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                while (true){
                    writer.write(queue.take());
                }
            }catch (InterruptedException ignored){
                //Thread.currentThread().interrupt();
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
