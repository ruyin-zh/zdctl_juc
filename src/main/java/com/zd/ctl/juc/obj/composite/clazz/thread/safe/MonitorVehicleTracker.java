package com.zd.ctl.juc.obj.composite.clazz.thread.safe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ruyin_zh
 * @date 2020-07-07
 * @title
 * @description 4.4-基于监视器模式的车辆追踪
 */
public class MonitorVehicleTracker {

    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized void setLocations(String carId,int x, int y){
        MutablePoint point = locations.get(carId);
        if (point == null){
            throw new IllegalArgumentException("No such carId:" + carId);
        }

        point.x = x;
        point.y = y;
    }

    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> locations){
        Map<String,MutablePoint> results = new HashMap<>();
        for (Map.Entry<String,MutablePoint> entry : locations.entrySet()){
            results.put(entry.getKey(),entry.getValue());
        }
        return Collections.unmodifiableMap(results);
    }

    /**
     * @author ruyin_zh
     * @date 2020-07-07
     * @title
     * @description 4.5-与java.awt.Point类似的可变Point类
     * @eval bad
     */
    public class MutablePoint {

        public int x,y;

        public MutablePoint() {
            x = 0;
            y = 0;
        }

        public MutablePoint(MutablePoint mp) {
            this.x = mp.x;
            this.y = mp.y;
        }
    }
}
