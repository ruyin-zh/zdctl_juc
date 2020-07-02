package com.zd.ctl.juc;

import com.zd.ctl.juc.thread.safety.lock.Widget;
import org.junit.jupiter.api.Test;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @description
 */
public class WidgetTest {

    @Test
    public void doSomethingTest(){
        Widget widget = new Widget().new LoggingWidget();
        widget.doSomething();
    }

    @Test
    public void strPrintTest(){
        String s = String.format("%0" + 3 + "d",0).replace("0"," important");
        System.out.println(s);
    }

}
