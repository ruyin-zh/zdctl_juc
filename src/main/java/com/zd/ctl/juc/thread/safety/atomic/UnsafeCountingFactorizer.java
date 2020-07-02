package com.zd.ctl.juc.thread.safety.atomic;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @title
 * @description 2.2-在没有同步的情况下统计已请求数量的Servlet
 * @eval bad
 */
public class UnsafeCountingFactorizer extends GenericServlet {

    private long count = 0;

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(bi);
        ++count;
        encodeIntoResponse(servletResponse,factors);
    }

    public long getCount() {
        return count;
    }

    private BigInteger extractFromRequest(ServletRequest request){

        return null;
    }

    private BigInteger[] factor(BigInteger bi){

        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] lastFactors){}
}
