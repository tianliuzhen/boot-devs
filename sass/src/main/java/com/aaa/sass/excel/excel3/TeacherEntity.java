package com.aaa.sass.excel.excel3;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/12
 */
@ExcelTarget("teacherEntity")
@Data
public class TeacherEntity implements java.io.Serializable {

    private String id;
    /**
     * name
     */
    @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1",needMerge = true,  isImportField = "true_major,true_absent")
    private String name;
}
