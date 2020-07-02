package com.zd.ctl.juc.thread.safety.state.lock;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-06-29
 * @title liveness与performance
 * @description 2.8-缓存最近执行因数分解的数值及其计算结果的Servlet
 */
public class CachedFactorizer extends GenericServlet {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = null;
        synchronized (this){
            ++hits;
            if (bi.equals(lastNumber)){
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }

        if (factors == null){
            factors = factor(bi);
            synchronized (this){
                lastNumber = bi;
                lastFactors = factors;
            }
        }

        encodeIntoResponse(servletResponse,factors);
    }


    private BigInteger extractFromRequest(ServletRequest request){

        return null;
    }

    private BigInteger[] factor(BigInteger bi){

        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] lastFactors){}
}
