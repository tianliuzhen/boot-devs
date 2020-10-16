package com.aaa.sass.excel.excel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ryan
 * @Description: Bean工具类
 * @Date: Created in 下午3:30 2018/6/5
 * @Modified By:
 */
public class BeanCopyUtil {
    /**
     * Bean之间的相同属性的复制
     *
     * @param source     提取属性的对象
     * @param target     复制属性到该对象
     * @param ignoreNull 是否要忽略null
     */
    public static void copyProperties(Object source, Object target, boolean ignoreNull) {
        if (ignoreNull) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } else {
            BeanUtils.copyProperties(source, target);
        }
    }

    /**
     * 获取Bean中为null的属性名
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
