package com.zd.ctl.juc.task.exec;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author ruyin_zh
 * @date 2020-07-29
 * @title
 * @description 6.17-在预定时间内请求旅行报价
 */
public class TravelQuoteHandler {

    private final ExecutorService executor;

    public TravelQuoteHandler(ExecutorService executor) {
        this.executor = executor;
    }


    private class QuoteTask implements Callable<TravelQuote> {
        private final TravelCompany company;
        private final TravelInfo travelInfo;

        public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
            this.company = company;
            this.travelInfo = travelInfo;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(travelInfo);
        }


        public TravelQuote getFailureQuote(Throwable throwable){

            return null;
        }

        public TravelQuote getTimeoutQuote(Throwable throwable){

            return null;
        }
    }

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                   Comparator<TravelQuote> ranking, long time, TimeUnit unit)
                                    throws InterruptedException{

        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies){
            tasks.add(new QuoteTask(company,travelInfo));
        }

        List<Future<TravelQuote>> futures = executor.invokeAll(tasks,time,unit);
        List<TravelQuote> quotes = new ArrayList<>();

        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> future : futures){
            QuoteTask task = taskIter.next();

            try {
                quotes.add(future.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e){
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        return quotes;
    }


    public class TravelCompany {

        public TravelQuote solicitQuote(TravelInfo travelInfo){


            return null;
        }
    }

    public class TravelInfo {

    }


    public class TravelQuote{

    }
}
