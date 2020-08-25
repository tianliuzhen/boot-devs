package com.aaa.wechat.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Random;

/**
 * @author liuzhen.tian
 * @version 1.0 CommonUtils.java  2020/8/25 14:59
 */
public class CommonUtils {
    /**
     * 生成指定长度的随机串
     * @param length
     * @return
     */
    public static String getRandomStringByLength(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    /**
     * 截取中文字符串
     * @param str
     * @param i
     * @return
     */
    public static String cutString(String str, int i) {
        int length = str.getBytes().length;
        if (length <= i) {
            return str;
        }
        String tempStr=null;
        StringBuilder resultStr=new StringBuilder();
        int len=0;
        int sum=0;
        for(int j=0;j<str.length();j++){
            tempStr=String.valueOf(str.charAt(j));
            len=tempStr.getBytes().length;
            //判断是否是汉字
            if(len>1){
                sum=sum+len;
                //判断字节数是否已越界
                if(sum<=i){
                    resultStr.append(tempStr);
                }else{
                    break;
                }
            }else{
                sum=sum+1;
                if(sum<=i){
                    resultStr.append(tempStr);
                }else{
                    break;
                }
            }
        }
        return resultStr.toString();
    }


    /**
     * 将 金额 元单位 转成 分单位
     * 示例值：2.56 --> 256
     * @param bigDecimal
     * @return
     */
    public static Integer convertBigDecimalToInteger(BigDecimal bigDecimal){
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100)).setScale(0);
        String s = multiply.toString();
        Integer result = Integer.valueOf(s);
        return result;
    }

    /**
     * 将 金额 分单位 转成 元单位
     * 示例值：256 --> 2.56
     * @param integer
     * @return
     */
    public static BigDecimal convertIntegerToBigDecimal(Integer integer){
        BigDecimal b = new BigDecimal(integer);
        BigDecimal result = b.divide(new BigDecimal(100)).setScale(2);
        return result;
    }
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
