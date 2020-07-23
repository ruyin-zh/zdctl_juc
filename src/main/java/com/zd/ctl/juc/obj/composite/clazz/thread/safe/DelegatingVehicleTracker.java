package com.zd.ctl.juc.obj.composite.clazz.thread.safe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ruyin_zh
 * @date 2020-07-07
 * @title
 * @description 4.6-将线程安全委托给ConcurrentHashMap
 */
public class DelegatingVehicleTracker {


    private final Map<String,Point> locations;
    private final Map<String,Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> locations) {
        this.locations = new ConcurrentHashMap<>();
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        //实时的视图
        //return unmodifiableMap;
        //特定时间点的视图
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String carId){
        return locations.get(carId);
    }

    public void setLocation(String carId, int x, int y){
        if (locations.replace(carId, new Point(x,y)) == null){
            throw new IllegalArgumentException("invalid vehicle carId:" +carId);
        }
    }

    /**
     * @author ruyin_zh
     * @date 2020-07-07
     * @title
     * @description 4.7-不可变Point类
     * @eval bad
     */
    public class Point {

        public final int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
