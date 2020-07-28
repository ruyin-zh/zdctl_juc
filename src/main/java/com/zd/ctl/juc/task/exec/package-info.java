/**
 * @author ruyin_zh
 * @date 2020-07-28
 * @title
 * @description
 */
package com.zd.ctl.juc.task.exec;


/**
 *
 *
 * 6.2.5、延迟任务与周期任务
 *
 * Timer在执行所有定时任务时只会创建一个线程:若其中某个人物的执行时间过长,那么将破坏其它TimeTask的定时准确性;
 * Timer的另一个问题是若TimeTask抛出了一个未检查的异常,那么Timer将表现出意外的行为:终止定时线程,
 *      已经被调度但未执行的TimerTask将不会执行,新的任务也不能够调度;
 *
 *
 * DelayQueue实现BlockingQueue
 * DelayQueue管理着一组Delayed对象:每个Delayed对象都有一个相应的延迟时间,在DelayQueue中只有某个元素逾期后,
 *      才能从DelayQueue中执行take操作;
 *
 *
 * CompletableFuture
 * FutureTask
 * CompletionService
 * ExecutorCompletionService
 *
 * */