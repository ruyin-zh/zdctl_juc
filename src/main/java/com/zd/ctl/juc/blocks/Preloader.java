package com.zd.ctl.juc.blocks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ruyin_zh
 * @date 2020-07-24
 * @title
 * @description 5.12-使用FutureTask预载稍后需要的数据
 */
public class Preloader {

    private final FutureTask<ProductInfo> task = new FutureTask<>(() -> loadProductInfo());
    private final Thread thread = new Thread(task);


    public void start(){
        thread.start();
    }

    public ProductInfo get() throws InterruptedException {
        try {
            return task.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            throw launderThrowable(cause);
        }
    }

    private ProductInfo loadProductInfo(){
        //从db获取数据
        return null;
    }

    public class ProductInfo {}


    public static RuntimeException launderThrowable(Throwable cause){
        if (cause instanceof RuntimeException){
            return (RuntimeException) cause;
        } else if (cause instanceof Error){
            throw (Error) cause;
        }else {
            throw new IllegalStateException("Not checked",cause);
        }
    }
}
