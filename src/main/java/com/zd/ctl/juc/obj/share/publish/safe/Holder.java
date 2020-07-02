package com.zd.ctl.juc.obj.share.publish.safe;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title 正确发布
 * @description 3.15-由于未被正确发布,因此这个类可能出现故障
 * @detail  在多线程情况下,由于没有使用同步来确保Holder对相对其他线程可见,因此Holder是非安全的;
 *          首先除了发布对象的线程外,其他线程看到的Holder域是一个失效值,可能看到一个空引用或者之前的旧值;
 *          更甚者线程看到的Holder引用是最新的,但是状态值确是失效的;
 */
public class Holder {

    private int hold;

    public Holder(int hold) {
        this.hold = hold;
    }

    public void assertSanity(){
        if (hold != hold){
            throw new AssertionError("This statement is false.");
        }
    }
}
