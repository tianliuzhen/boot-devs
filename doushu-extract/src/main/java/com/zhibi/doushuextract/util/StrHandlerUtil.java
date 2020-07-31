package com.zhibi.doushuextract.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liuzhen.tian
 * @version 1.0 StrUtil.java  2020/7/31 18:20
 */
public class StrHandlerUtil {

    /**
     * regex对输入特殊字符转义
     * @param keyword
     * @return
     */
   public static String  escapeExprSpecialWord(String keyword) {

        if (StringUtils.isNotBlank(keyword)) {

            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };

            for (String key : fbsArr) {

                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }

        return keyword;
    }
}
