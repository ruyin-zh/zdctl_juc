package com.zd.ctl.juc.thread.safety.lock;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @title 内置锁
 * @description 2.6-该Servlet能正确缓存最新的计算结果,但是并发性非常差
 * @eval bad
 */
public class SynchronizedFactorizer extends GenericServlet {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    @Override
    public synchronized void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        BigInteger bi = extractFromRequest(servletRequest);
        if (bi.equals(lastNumber)){
            encodeIntoResponse(servletResponse,lastFactors);
        }else {
            BigInteger[] factors = factor(bi);
            lastNumber = bi;
            lastFactors = factors;
            encodeIntoResponse(servletResponse,factors);
        }

    }

    private BigInteger extractFromRequest(ServletRequest request){

        return null;
    }

    private BigInteger[] factor(BigInteger bi){

        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] lastFactors){}
}
