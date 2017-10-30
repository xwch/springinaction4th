package com.xwch.bean;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * 用户发布的简短状态更新
 * Created by xwch on 2017/10/18.
 */
@Getter
@Setter
@ToString
public class Spittle {

    /**
     * 主键
     */
    private Long id;

    /**
     * 内容
     */
    private String message;

    /**
     * 时间戳
     */
    private Date time;

    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;

    public Spittle(){}

    public Spittle(String message, Date time){
        this(null,message,time,null,null);
    }

    public Spittle(Long id,String message, Date time, Double latitude, Double longitude){
        this.id = id;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * 这2个方法重写是用来干嘛的，没看懂
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this,o,"id","time");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,"id","time");
    }
}
