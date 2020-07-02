package com.zd.ctl.juc.obj.share.visibility;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title
 * @description 3.4-数绵羊
 * @detail 仅当volatile变量能简化代码的实现以及对同步策略的验证时,才应该使用它们;
 *         volatile变量常用作某个操作完成、发生中断或者状态的标志;
 *         加锁机制既可以确保可见性又可以确保原子性,而volatile变量只能确保可见性;
 */
public class CountSheep {

    private boolean asleep;

    private class CountAction implements Runnable {

        @Override
        public void run() {
            //仅用于同步策略验证
            while (!asleep){
                countSomeSheep();
            }
        }


        private void countSomeSheep(){}
    }
}
