package com.zd.concurrent.test;

import java.util.concurrent.TimeUnit;

/**
 * @author ruyin_zh
 * @date 2020-06-30
 * @title
 * @description
 */
public class FileSearchTest {

    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch("/Users/ruyin/dev-dir/test","JedisWithLuaTest.java");

        Thread thread = new Thread(fileSearch);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        thread.interrupt();
    }
}
