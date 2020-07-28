package com.zd.ctl.juc.task.exec;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author ruyin_zh
 * @date 2020-07-28
 * @title
 * @description 6.9-错误的Timer行为
 */
public class OutOfTime {

    /**
     *
     *
     * 程序执行1秒就结束了,并抛出一个异常(Timer already cancelled),而非执行6秒钟再结束
     *
     * */
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new ThrowTask(),1);
        TimeUnit.SECONDS.sleep(5);
    }


    static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            //因为Timer不会捕获异常,在抛出异常之后程序将中断,并且TimerTask将不再执行,并且新的任务可不可被调度
            throw new RuntimeException();
        }
    }

}
