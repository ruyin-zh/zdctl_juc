package com.zd.ctl.juc.thread.safety.lock;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @title
 * @description 2.5-该Servlet在没有足够原子性保证的情况下对其最近计算结果进行缓存
 * @eval bad
 */
public class UnsafeCachingFactorizer extends GenericServlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        if (bi.equals(lastNumber.get())){
            encodeIntoResponse(servletResponse,lastFactors.get());
        }else {
            BigInteger[] factors = factor(bi);
            lastNumber.set(bi);
            lastFactors.set(factors);
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
