package com.zd.ctl.juc.task.exec;

import java.util.concurrent.Executor;

/**
 * @author ruyin_zh
 * @date 2020-07-27
 * @title
 * @description 6.5-为每个任务启动一个新线程Executor
 */
public class ThreadPerTaskExecWebServer implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }

}
