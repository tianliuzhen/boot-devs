package com.aaa.sass.excel.excel2;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/12
 */
@ExcelTarget("deptUtil")
@Data
public class DeptUtil {

    @Excel(name = "部门编号", width = 30, needMerge = true)
    private Integer id;

    @Excel(name = "部门名称", width = 30, needMerge = true)
    private String deptName;

    @ExcelCollection(name = "员工信息")
    private List<EmpUtil> emps;



}


