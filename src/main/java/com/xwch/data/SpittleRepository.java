package com.xwch.data;

import com.xwch.bean.Spittle;

import java.util.List;

/**
 * 数据访问接口
 * Created by xwch on 2017/10/18.
 */
public interface SpittleRepository {

    List<Spittle> findRecentSpittles();
    /**
     * 获取Spittle列表
     * @param max 返回列表中SpittleID的最大值
     * @param count 返回的列表个数
     * @return
     */
    List<Spittle> findSpittles(long max, int count);

    /**
     * 根据spittleI的查询Spittle
     * @param id
     * @return
     */
    Spittle findOne(long id);

    /**
     * 保存
     * @param spittle
     */
    void save(Spittle spittle);
}
