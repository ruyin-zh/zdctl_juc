package com.zd.ctl.juc.obj.composite.clazz.thread.safe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ruyin_zh
 * @date 2020-07-07
 * @title
 * @description 4.2-通过封闭机制来确保线程安全
 */
public class PersonSet {

    private final Set<Person> mySet = new HashSet<>();

    public synchronized boolean addPersion(Person p){
        return mySet.add(p);
    }

    public synchronized boolean containPerson(Person p){
        return mySet.contains(p);
    }

    public class Person {

    }

}
