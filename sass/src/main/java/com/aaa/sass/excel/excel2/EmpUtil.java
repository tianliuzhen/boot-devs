package com.aaa.sass.excel.excel2;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/12
 */
@ExcelTarget("empUtil")
@Data
public class EmpUtil {

    @Excel(name = "序号", width = 30, isColumnHidden = true)
    private Integer id;

    @Excel(name = "员工姓名", width = 30, groupName = "基本信息")
    private String empName;

    @Excel(name = "年龄", width = 30, type = 10, groupName = "基本信息")
    private Integer age;

    @Excel(name = "入职时间", width = 30, groupName = "工作信息", format = "yyyy/MM/dd HH:mm")
    private Date hiredate;

    @Excel(name = "薪酬", width = 30, type = 10, groupName = "工作信息")
    private BigDecimal salary;



}

