package com.zd.ctl.juc.obj.share.publish.escape;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ruyin_zh
 * @date 2020-07-02
 * @title
 * @description 3.5-发布一个对象
 */
public class PublishObject {

    //knowSecrets集合对外发布,此时外部和内部均可对其直接进行修改
    public static Set<Secret> knowSecrets;

    public void initialize(){
        knowSecrets = new HashSet<>();
    }



    public class Secret {}
}
