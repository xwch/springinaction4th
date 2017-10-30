package com.xwch.data;

import com.xwch.bean.Spitter;

/**
 * Created by xwch on 2017/10/22.
 */
public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
