package com.zd.ctl.juc.obj.share.seal;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title 线程封闭--栈封闭
 * @description 3.9-基本类型的局部变量与引用变量的线程封闭性
 * @detail
 *
 */
public class StackSeal {



    public int loadTheArk(Collection<Animal> candidates){
        SortedSet<Animal> animals;
        int numPairs = 0;

        Animal candidate = null;

        //animal被封闭在方法中,不要使他们逸出
        animals = new TreeSet<>();
        animals.addAll(candidates);
        for (Animal animal : animals){
            if (candidate == null || !candidate.isPotentialMate(animal)){
                candidate = animal;
            }else {
                //
                doSomething(candidate,animal);

                ++numPairs;
                candidate = null;
            }
        }

        return numPairs;
    }

    public void doSomething(Animal candidate,Animal animal){}

    public abstract class Animal implements Comparable<Animal> {

        public boolean isPotentialMate(Animal animal){

            return false;
        }
    }
}
