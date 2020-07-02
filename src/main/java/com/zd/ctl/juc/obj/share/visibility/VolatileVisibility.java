package com.zd.ctl.juc.obj.share.visibility;

/**
 * @author ruyin_zh
 * @date 2020-06-29
 * @title
 * @description
 */
public class VolatileVisibility {

    private volatile static boolean ready;
    private volatile static int number;


    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            number++;
            while (!ready){
                System.out.println(number);
                Thread.yield();
            }
            System.out.println(number);
        }
    }


    /**
     *
     * 因为编译器、处理器及运行时都可能会对指令顺序进行重排序,可能出现以下四种情况:
     * 1、ready:false;number:0
     * 2、ready:false;number:42
     * 3、ready:true;number:0
     * 4、ready:true;number:42
     *
     * */
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
