package com.zd.ctl.juc.task.exec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ruyin_zh
 * @date 2020-07-28
 * @title
 * @description 6.10-串行地渲染页面元素
 */
public class SingleThreadRenderer {

    public void renderPage(CharSequence source){
        //渲染文本信息
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();

        //提取图片信息
        for (ImageInfo imageInfo : scanForImageInfo(source)){
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData id : imageData){
            //渲染图片信息
            renderImage(id);
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



