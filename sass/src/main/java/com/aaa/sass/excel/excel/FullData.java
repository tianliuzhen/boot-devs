package com.aaa.sass.excel.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * description: 满减满折统计数据
 *
 * @author 蔡荣茂(cairongmao @ haoxiaec.com)
 * @version 1.0
 * @date 2019/3/18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class FullData {

    @ApiModelProperty("活动id")
    private String actId;

    @ApiModelProperty("日期")
    private LocalDate date;

    @ApiModelProperty("下单数")
    private String orderNum;

    @ApiModelProperty("下单人数")
    private String orderUserNum;

    @ApiModelProperty("支付数")
    private String payNum;

    @ApiModelProperty("支付人数")
    private String payUserNum;

    @ApiModelProperty("实付总额")
    private String sumMoneyPaid;

    @ApiModelProperty("优惠总额")
    private String discountMoney;

    @ApiModelProperty("优惠券总额")
    private String couponMoney;

    @ApiModelProperty("该活动所影响的优惠金额")
    private String discountMoneyByAct;

    public void setDiscountMoneyByAct(){
        this.discountMoneyByAct = new BigDecimal(this.discountMoney).subtract(new BigDecimal(this.couponMoney)).toString();
    }

}
