package com.zd.ctl.juc.obj.share.invariability;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title 不可变--使用volatile类型来发布不可变对象
 * @description 3.13-使用指向不可变容器对象的volatile类型引用以缓存最新的结果
 */
public class VolatileCachedFactorizer extends GenericServlet {

    private volatile OneValueCache cache = new OneValueCache(null,null);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = cache.getFactors(bi);
        if (factors == null){
            factors = factor(bi);
            cache = new OneValueCache(bi,factors);
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
