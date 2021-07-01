package com.aaa.wechat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市code
     */
    private Integer cityCode;
}
