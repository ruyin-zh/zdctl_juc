package com.zd.concurrent.test;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author ruyin_zh
 * @date 2020-06-29
 * @title
 * @description
 */
public class CalculatorTest {

    public static void main(String[] args) {

        System.out.printf("Minimum priority: %s\n",Thread.MIN_PRIORITY);
        System.out.printf("Normal priority: %s\n",Thread.NORM_PRIORITY);
        System.out.printf("Maximum priority: %s\n",Thread.MAX_PRIORITY);

        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];
        for (int i = 0; i < 10;i++){
            threads[i] = new Thread(new Calculator());
            if (i % 2 == 0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("My-thread-" + i);
        }

        try(FileWriter fw = new FileWriter("log.txt");
            PrintWriter pw = new PrintWriter(fw)){
            for (int i = 0; i < 10; i++){
                pw.println("Main: State of thread " + i + ":" + threads[i].getState());
                states[i] = threads[i].getState();
            }
            for (int i = 0; i < 10; i++){
                threads[i].start();
            }

            boolean finish = false;
            while (!finish){
                for (int i = 0; i < 10; i++){
                    if (threads[i].getState() != states[i]){
                        writeThreadInfo(pw,threads[i],states[i]);
                        states[i] = threads[i].getState();
                    }
                }

                finish = true;

                for (int i = 0; i < 10; i++){
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeThreadInfo(PrintWriter pw,
                                       Thread thread,
                                       Thread.State state){
        pw.printf("Main: Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main: Priority %d\n",thread.getPriority());
        pw.printf("Main: Old state %s\n",state);
        pw.printf("Main: New state %s\n",thread.getState());
        pw.printf("Main: ****************************\n");
    }
}
