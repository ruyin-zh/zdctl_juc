/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title
 * @description
 */
package com.zd.ctl.juc.obj.share.publish.safe;

/**
 *
 * 安全发布
 *
 * 要安全地发布一个对象,则该对象的引用及对象的状态必须同时对其他线程可见
 * 安全发布的常用模式
 * 1、在静态初始化方法中初始化一个对象引用;
 * 2、将对象的引用保存到volatile类型的域或者AtomicReference对象中;
 * 3、将对象的引用保存到某个正确构造对象的final类型域中;
 * 4、将对象的引用保存到一个由锁保护的域中;
 *
 * */