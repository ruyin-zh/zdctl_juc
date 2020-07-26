package com.zd.ctl.juc.blocks;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author ruyin_zh
 * @date 2020-07-27
 * @title
 * @description 5.20-在因式分解Servlet中使用Memoizer来缓存结果
 */
public class Factorizer extends GenericServlet {

    private final Computable<BigInteger,BigInteger[]> c = (arg) -> factor(arg);
    private final Computable<BigInteger,BigInteger[]> cache = new Memoizer<>(c);

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        try {
            BigInteger i = extractFromRequest(request);
            encodeIntoResponse(response,cache.compute(i));
        }catch (InterruptedException e){
            encodeError(response,"factorization interrupted");
        }

    }


    private BigInteger extractFromRequest(ServletRequest request){

        return null;
    }

    private BigInteger[] factor(BigInteger bi){

        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] lastFactors){}

    private void encodeError(ServletResponse response, String reason){}

}
