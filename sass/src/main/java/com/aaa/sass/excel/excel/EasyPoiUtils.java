package com.aaa.sass.excel.excel;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/12
 */
@Slf4j
public class EasyPoiUtils {


    /**
     * 导入Excel数据
     * @param filePath 文件路径
     * @param titleRows 标题的行数
     * @param headerRows 表头的行数
     * @param pojoClass 要转换的类
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new IllegalArgumentException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
        return list;
    }


    /**
     * 导入Excel数据
     * @param file MultipartFile文件
     * @param pojoClass 要转换的类
     * @param <T>
     * @return
     */
    public static <T> ExcelImportResult<T> importExcel(MultipartFile file, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams importParams = new ImportParams();


        // 需要验证
//        importParams.setNeedVerfiy(true);


        try {
            ExcelImportResult<T> result =
                    ExcelImportUtil.importExcelMore(file.getInputStream(), pojoClass, importParams);
            return result;
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 下载
     * @param workbook
     * @param response
     * @throws IOException
     */
    public static void download(Workbook workbook, HttpServletResponse response) throws IOException {
        download(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()), workbook,response);
    }

    /**
     * 得到Workbook对象 =>  excel 能兼容03和07
     * HSSF对应97-2003版本的Excel，XSSF则对应2007版本的Excel。
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkBook(MultipartFile file) throws IOException{
        InputStream is = file.getInputStream();
        Workbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (Exception ex) {
            is =file.getInputStream();
            hssfWorkbook = new XSSFWorkbook(is);
        }
        return hssfWorkbook;
    }
    /**
     * 下载
     * @param workbook
     * @param response
     * @throws IOException
     */
    public static void download(String fileName,Workbook workbook, HttpServletResponse response) throws IOException {

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");


        workbook.write(response.getOutputStream());
    }


    /**
     * 获取导出excel动态列  实体集合
     * @param columnMap 所有候选列 "column1" => "列名1"
     * @param columns 需要导出的列 ["column1","column2"]
     * @return
     */
    public static List<ExcelExportEntity> getExcelExportEntityList(Map<String,String> columnMap, List<String> columns) {
        List<ExcelExportEntity> colList = new ArrayList();
        if (columns.isEmpty()) {
            columnMap.forEach((k,v) -> {
                colList.add(new ExcelExportEntity(v,k));
            });
        } else {
            columnMap.forEach((k,v) -> {
                if (columns.contains(k)) {
                    colList.add(new ExcelExportEntity(v,k));
                }
            });
        }
        return colList;
    }


}
