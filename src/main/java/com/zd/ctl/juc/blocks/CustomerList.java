package com.zd.ctl.juc.blocks;

import java.util.List;

/**
 * @author ruyin_zh
 * @date 2020-07-22
 * @title
 * @description
 */
public interface CustomerList<T extends Iterable> {

    List<? super T> get();

    void set(List<? extends T> list);
}
