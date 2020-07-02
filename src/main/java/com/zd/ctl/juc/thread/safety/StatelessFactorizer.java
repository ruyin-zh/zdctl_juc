package com.zd.ctl.juc.thread.safety;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-06-28
 * @title 无状态对象一定是线程安全的
 * @description 2.1-无状态的一个Servlet示例
 */
public class StatelessFactorizer extends GenericServlet {


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(bi);
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
