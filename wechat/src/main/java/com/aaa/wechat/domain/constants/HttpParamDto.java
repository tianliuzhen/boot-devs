package com.aaa.wechat.domain.constants;

import lombok.Data;

@Data
public class HttpParamDto {
    public static final String METHOD_TYPE_GET = "GET";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String CHARSET_GBK = "GBK";
    /**
     * 默认连接超时为20秒
     */
    public static final int CONNECTION_TIME_OUT = 20000;
    /**
     * 默认数据传输超时为20秒
     */
    public static final int SO_TIME_OUT = 20000;

    public static final int DEFAULT_CONNECT_TIME_OUT = 10000;

    public static final int DEFAULT_READ_TIME_OUT = 30000;
    // 默认最小值的阀值
    public static final int DEFAULT_MIN_VALUE = 3000;
    // 默认最大值的阀值
    public static final int DEFAULT_MAX_VALUE = 10000;
    // 内容类型JSON
    public static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * 与外部交互的统一接口url
     */
    private String url;
    /**
     * post请求发送的参数串
     */
    private String content = "";
    /**
     * 交互方式,默认为get
     */
    private String methodType = METHOD_TYPE_GET;
    /**
     * 编码类型,默认是utf-8
     */
    private String charset = DEFAULT_CHARSET;
    /**
     * 超时时间
     */
    private int connectionTimeOut = CONNECTION_TIME_OUT;
    /**
     * 超时时间
     */
    private int soTimeOut = SO_TIME_OUT;
    /**
     * 是否压缩，默认为不压缩
     */
    private boolean compress = false;
    /**
     * 是否返回单行，默认是false，不返回单行
     */
    private boolean returnSingle = false;
}
