package com.zd.ctl.juc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @description
 */
public class HelloWorldTest {

    @Test
    @DisplayName("junit5初试")
    public void firstTest(){
        System.out.println("Hello World!");
    }

    @Test
    @DisplayName("异常测试")
    public void exceptionTest(){
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,() -> System.out.println(1 % 0));
        System.out.println(exception.getMessage());
    }

    @Test
    @DisplayName("超时测试")
    public void timeOutTest(){
        Assertions.assertTimeout(Duration.ofMillis(1000),() -> Thread.sleep(500));
    }


    @RepeatedTest(10)
    @DisplayName("重复测试")
    public void repeatTest(){
        System.out.println("Hello World!");
    }

    @Test
    @Disabled
    @DisplayName("禁用测试")
    public void disableTest(){

    }

    @TestFactory
    @DisplayName("动态测试")
    public Iterator<DynamicTest> dynamicTests(){
        return Arrays.asList(DynamicTest.dynamicTest("first",() -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("second",() -> Assertions.assertEquals(4,2*2))).
                iterator();
    }

    @Test
    @DisplayName("调用动态测试用例")
    public void testDynamicTest(){
        Iterator<DynamicTest> dynamicTestIterator = dynamicTests();
        try {
            dynamicTestIterator.next().getExecutable().execute();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
