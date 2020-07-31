package com.zhibi.doushuextract.util;

import lombok.Data;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 PageHelper.java  2020/7/31 17:17
 */
@Data
public class PageUtils<T> {
    private long currentPage;
    private long total;
    private long pageSize;
    private List<T> list;

    public PageUtils(long pageNum, long total, long pageSize, List<T> list) {
        this.currentPage = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        this.list = list;
    }

    public PageUtils(long pageNum, long pageSize, List<T> list) {
        this.currentPage = pageNum;
        this.pageSize = pageSize;
        this.list = list;
    }
}
