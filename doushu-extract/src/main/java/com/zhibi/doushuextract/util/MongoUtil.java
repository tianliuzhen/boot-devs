package com.zhibi.doushuextract.util;

/**
 * @author liuzhen.tian
 * @version 1.0 MongoUtil.java  2020/7/31 17:17
 */

import lombok.Data;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 PageHelper.java  2020/7/31 17:17
 */
@Data
@Component
public class MongoUtil<T> {
    public Integer pageSize;
    private Integer currentPage;


    public void start(Integer currentPage, Integer pageSize, Query query) {
        pageSize = pageSize == 0 ? 10 : pageSize;
        query.limit(pageSize);
        query.skip((currentPage - 1) * pageSize);
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public PageUtils PageUtils(long total, List<T> list) {
        return new PageUtils(this.currentPage, total, this.pageSize, list);
    }

    public PageUtils PageUtils(List<T> list) {
        return new PageUtils(this.currentPage, this.pageSize, list);
    }

    public PageUtils PageUtils(long currentPage, long total, long pageSize, List<T> list) {
        return new PageUtils(currentPage, total, pageSize, list);
    }

    public PageUtils PageUtils(long currentPage, long pageSize, List<T> list) {
        return new PageUtils(currentPage, pageSize, list);
    }


    /**
     * 用于模糊查询忽略大小写
     *
     * @param string
     * @return
     */
    public Pattern getPattern(String string) {
        Pattern pattern = Pattern.compile("^.*" + string + ".*$", Pattern.CASE_INSENSITIVE);
        return pattern;
    }
}

