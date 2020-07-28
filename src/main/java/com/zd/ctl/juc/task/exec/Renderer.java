package com.zd.ctl.juc.task.exec;

import com.zd.ctl.juc.blocks.Preloader;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 6.15-使用CompletionService使页面元素在下载完成后立即显示出来
 */
public class Renderer {

    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    public void renderPage(CharSequence source){
        List<ImageInfo> infoList = scanForImageInfo(source);

        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        for (ImageInfo info : infoList){
            completionService.submit(() -> info.downloadImage());
        }

        renderText(source);

        try {
            for (int i = 0; i < infoList.size(); i ++){
                Future<ImageData> future = completionService.take();
                ImageData data = future.get();
                renderImage(data);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e){
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
