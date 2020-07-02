package com.zd.ctl.juc.obj.share.seal;

import java.sql.Connection;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title 线程封闭--ThreadLocal类
 * @description 3.10-使用ThreadLocal来维持线程封闭性
 * @detail ThreadLocal变量类似于全局变量,它能降低的可重用性,
 *         并在类之间引入隐含的耦合性,因此在使用时要格外小心
 */
public class ThreadLocalSeal {

    private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> DriverManager.getConnection("jdbc:mysql://xxxx"));


    public static Connection getConnection(){
        return connectionHolder.get();
    }


    private static class DriverManager {

        public static Connection getConnection(String url){
            return null;
        }
    }
}
