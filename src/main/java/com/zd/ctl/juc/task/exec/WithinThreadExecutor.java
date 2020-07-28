package com.zd.ctl.juc.task.exec;

import java.util.concurrent.Executor;

/**
 * @author ruyin_zh
 * @date 2020-07-28
 * @title
 * @description 6.6-在调用线程中以同步方式执行所有任务的Executor
 */
public class WithinThreadExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
