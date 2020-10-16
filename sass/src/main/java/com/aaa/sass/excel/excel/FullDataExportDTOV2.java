package com.aaa.sass.excel.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/11
 */
@Data
public class FullDataExportDTOV2 {

    @Excel(name = "日期")
    private String date;


    @Excel(name = "下单人数")
    private String orderUserNum;

    @Excel(name = "付款人数")
    private String payNum;

    @Excel(name = "下单数量")
    private String orderNum;

    @Excel(name = "付款单数")
    private String payUserNum;

    @Excel(name = "实付总额")
    private String sumMoneyPaid;

    @Excel(name = "优惠总额")
    private String discountMoney;


}
