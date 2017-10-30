package com.xwch.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by xwch on 2017/10/19.
 */

@Setter
@Getter
public class SpittleFormDTO {

    @NotNull
    @Size(min = 1,max = 140)
    private String message;

    /**
     * 经度
     */
    @Min(-180)
    @Max(180)
    private Double longitude;

    /**
     * 纬度
     */
    @Min(-90)
    @Max(90)
    private  Double latitude;
}
