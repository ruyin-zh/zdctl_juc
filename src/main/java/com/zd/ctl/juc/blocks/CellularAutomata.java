package com.zd.ctl.juc.blocks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ruyin_zh
 * @date 2020-07-24
 * @title
 * @description 5.15-通过CyclicBarrier协调细胞自动衍生系统中的计算
 */
public class CellularAutomata {

    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.workers = new Worker[count];

        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                mainBoard.commitNewValues();
            }
        });

        for (int i = 0; i < count; i++){
            workers[i] = new Worker(mainBoard.getSubBoard(count,i));
        }
    }


    public void start(){
        for (int i = 0; i < workers.length; i++){
            new Thread(workers[i]).start();
        }

        mainBoard.waitForConvergence();
    }


    public class Board {


        public void commitNewValues(){}


        public Board getSubBoard(int count, int i){

            return null;
        }


        public void waitForConvergence(){}

        public boolean hasConverged(){

            return false;
        }


        public int getMaxX(){

            return 0;
        }

        public int getMaxY(){

            return 0;
        }


        public void setNewValue(int x, int y, int xy){}
    }

    public class Worker implements Runnable {

        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {

            while (!board.hasConverged()){
                for (int x = 0; x < board.getMaxX(); x++){
                    for (int y = 0; y < board.getMaxY(); y++){
                        board.setNewValue(x,y,computeValue(x,y));
                    }
                }

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }


        public int computeValue(int x, int y){

            return x * y;
        }
    }
}
