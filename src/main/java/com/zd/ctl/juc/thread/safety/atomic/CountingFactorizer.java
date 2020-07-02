package com.zd.ctl.juc.thread.safety.atomic;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xzwang
 * @date 2020-06-28
 * @title 复合操作
 * @description 2.4-使用AtomicLong类型来统计已处理请求数量
 */
public class CountingFactorizer extends GenericServlet {

    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(bi);
        count.incrementAndGet();
        encodeIntoResponse(servletResponse,factors);
    }

    public long getCount() {
        return count.get();
    }

    private BigInteger extractFromRequest(ServletRequest request){

        return null;
    }

    private BigInteger[] factor(BigInteger bi){

        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] lastFactors){}
}
