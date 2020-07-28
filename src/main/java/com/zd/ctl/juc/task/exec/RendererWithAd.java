package com.zd.ctl.juc.task.exec;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 6.16-在指定时间内获取广告信息
 */
public class RendererWithAd {

    private final long TIME_BUDGET = 10000;
    private final Ad DEFAULT_AD = new Ad();

    private final ExecutorService executor;

    public RendererWithAd(ExecutorService executor) {
        this.executor = executor;
    }

    public Page renderPageWithAd() throws InterruptedException {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> future = executor.submit(new FetchAdTask());
        //在等待广告的同时显示页面
        Page page = renderPageBody();
        Ad ad;

        try {
            long timeLeft = endNanos - System.nanoTime();
            ad = future.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            future.cancel(true);
        }

        page.setAd(ad);
        return page;
    }

    public Page renderPageBody(){

        return null;
    }

    public class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            return null;
        }
    }

    public class Ad {

    }

    public class Page {

        private Ad ad;

        public Ad getAd() {
            return ad;
        }

        public void setAd(Ad ad) {
            this.ad = ad;
        }
    }
}
