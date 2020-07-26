package com.zd.ctl.juc.blocks;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ruyin_zh
 * @date 2020-07-24
 * @title
 * @description 5.8-桌面搜索程序当中的生产者与消费者
 */
public class DiskCrawler {




    /**
     *
     * 生产者:对文件进行归档
     *
     * */
    public static class FileCrawler implements Runnable {
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
            this.fileQueue = fileQueue;
            this.fileFilter = fileFilter;
            this.root = root;
        }

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


        private void crawl(File root) throws InterruptedException {
            File[] files = root.listFiles();
            if (files != null){
                for (File file : files){
                    if (file.isDirectory()){
                        crawl(file);
                    }else if (!alreadyIndexed(file)){
                        fileQueue.put(file);
                    }
                }
            }
        }

        private boolean alreadyIndexed(File root){
            return false;
        }
    }

    /**
     *
     * 消费者:为归档的文件建立索引
     *
     * */
    public static class Indexer implements Runnable {
        private final BlockingQueue<File> fileQueue;

        public Indexer(BlockingQueue<File> fileQueue) {
            this.fileQueue = fileQueue;
        }

        @Override
        public void run() {
            try {
                while (true){
                    indexFile(fileQueue.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

        private void indexFile(File file) {

        }
    }


    public static void main(String[] args) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(1024);
        FileFilter fileFilter = (pathName) -> true;

        File[] roots = null;
        for (File file : roots){
            new Thread(new FileCrawler(queue,fileFilter,file)).start();
        }

        for (int i = 0; i < 5; i++){
            new Thread(new Indexer(queue)).start();
        }
    }
}
