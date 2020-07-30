package com.zd.ctl.juc.task.cancel;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title 采用newTaskFor来封装非标准的取消
 * @description 7.12-通过newTaskFor将非标准的取消操作封装在一个任务中
 */
public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();

}
