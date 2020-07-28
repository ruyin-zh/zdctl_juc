package com.zd.ctl.juc.task.exec;

import com.zd.ctl.juc.blocks.Preloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ruyin_zh
 * @date 2020-07-28
 * @title
 * @description 6.13-使用Future等待图像下载
 */
public class FutureRenderer {

    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    public void renderPage(CharSequence source){

        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> imageTask = () -> {
            List<ImageData> result = new ArrayList<>();
            for (ImageInfo info : imageInfos){
                result.add(info.downloadImage());
            }

            return result;
        };

        Future<List<ImageData>> future = exec.submit(imageTask);
        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData){
                renderImage(data);
            }
        } catch (InterruptedException e) {
            //重新设置线程中断状态
            Thread.currentThread().interrupt();
            //因为不需要结果,故而直接取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw Preloader.launderThrowable(e.getCause());
        }
    }

    private void renderText(CharSequence source){

    }

    private void renderImage(ImageData imageData){

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source){
        return Collections.emptyList();
    }

    public class ImageData {

    }

    public class ImageInfo {

        public ImageData downloadImage(){

            return null;
        }

    }

}
