package com.aaa.wechat.utils;

import com.aaa.wechat.config.exception.BusinessException;
import com.aaa.wechat.domain.constants.CommonConstant;
import com.aaa.wechat.domain.constants.HttpParamDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


@Service
@Slf4j
public class HttpsUtils {



    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String sendRequest(String url, int connectTimeout, int readTimeout, String charset, boolean
            returnSingle) throws BusinessException {
        BufferedReader in = null;
        HttpURLConnection conn = null;
        try {
            if (StringUtils.isBlank(charset)) {
                charset = HttpParamDto.DEFAULT_CHARSET;
            }
            conn = getURLConnection(url, connectTimeout, readTimeout);
            in = new BufferedReader(new InputStreamReader(connect(conn),
                    charset));

            String result = null;
            result = getReturnResult(in, returnSingle);
            if (StringUtils.isBlank(result)) {
                throw new BusinessException("网络异常，" + url
                        + " 返回数据为空");
            }
            return result;
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException("网络IO异常[" + url + "]", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.warn("", e);
            }
        }
    }

    public static String sendRequest(String url) throws BusinessException {
        return sendRequest(url, HttpParamDto.DEFAULT_CONNECT_TIME_OUT, HttpParamDto.DEFAULT_READ_TIME_OUT,
                HttpParamDto.DEFAULT_CHARSET, false);
    }


    public static String sendRequest(String url, boolean returnSingle) throws BusinessException {
        return sendRequest(url, HttpParamDto.DEFAULT_CONNECT_TIME_OUT,
                HttpParamDto.DEFAULT_READ_TIME_OUT,
                HttpParamDto.DEFAULT_CHARSET, returnSingle);
    }

    public static String sendPostRequest(String url, String content, String charset, int connectTimeout, int
            readTimeout,
                                         boolean needCompress, String contentType, boolean returnSingle)
            throws BusinessException {
        BufferedReader in = null;
        HttpURLConnection httpConn = null;
        try {
            httpConn = getURLConnection(url, connectTimeout, readTimeout,
                    contentType);
            if (StringUtils.isBlank(charset)) {
                charset = HttpParamDto.DEFAULT_CHARSET;
            }
            InputStream stream = postConnect(httpConn, content, charset,
                    needCompress);

            in = new BufferedReader(new InputStreamReader(stream, charset));
            String result = getReturnResult(in, returnSingle);
            // log.debug("请求返回结果:" + result);
            if (StringUtils.isBlank(result)) {
                throw new BusinessException("网络异常，" + url
                        + "无法联通");
            }
            return result;
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException("网络IO异常[" + url + "]", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                 if (httpConn != null)
                 {
                 httpConn.disconnect();
                 }
            } catch (IOException e) {
                log.warn("", e);
            }
        }
    }

    public static String sendPostRequest(String url, String content, String charset) {
        return sendPostRequest(url, content, charset, HttpParamDto.DEFAULT_CONNECT_TIME_OUT,
                HttpParamDto.DEFAULT_READ_TIME_OUT);
    }

    public static String sendPostRequest(String url, String content, String charset,
                                         int connectTimeout, int readTimeout)
            throws BusinessException {
        return sendPostRequest(url, content, charset, connectTimeout,
                readTimeout, false);
    }

    public static String sendPostRequest(String url, String content, String charset,
                                         boolean needCompress) throws BusinessException {
        return sendPostRequest(url, content, charset,
                HttpParamDto.DEFAULT_CONNECT_TIME_OUT,
                HttpParamDto.DEFAULT_READ_TIME_OUT, needCompress);
    }

    public static String sendPostRequest(String url, String content, String charset,
                                         int connectTimeout, int readTimeout, String contentType)
            throws BusinessException {
        return sendPostRequest(url, content, charset, connectTimeout,
                readTimeout, false, contentType, false);
    }

    public static String sendPostRequest(String url, String content, String charset,
                                         int connectTimeout, int readTimeout, boolean needCompress)
            throws BusinessException {
        return sendPostRequest(url, content, charset, connectTimeout,
                readTimeout, needCompress, null, false);
    }

    /**
     * post方式发送请求
     *
     * @param url
     * @param content
     * @param connectTimeout
     * @param readTimeout
     * @param needCompress
     * @param sslContext
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    public static String sendPostRequest(String url, String content, String charset,
                                         int connectTimeout, int readTimeout, boolean needCompress,
                                         SSLContext sslContext) throws MalformedURLException, IOException {
        InputStream is = null;
        // BufferedReader in = null;
        HttpURLConnection httpConn = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        charset = StringUtils.isBlank(charset) ? HttpParamDto.DEFAULT_CHARSET
                : charset;
        try {
            httpConn = getURLConnection(url, connectTimeout, readTimeout, null);
            if (StringUtils.isBlank(charset)) {
                charset = HttpParamDto.DEFAULT_CHARSET;
            }
            is = postConnect(httpConn, content, charset, needCompress);
            int ch;
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(baos);
            while ((ch = bis.read()) != -1) {
                bos.write(ch);
            }
            bos.flush();
            bis.close();
            return new String(baos.toByteArray(), HttpParamDto.DEFAULT_CHARSET);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (httpConn != null) {
                    httpConn.disconnect();
                }
            } catch (Exception e) {
                throw new IOException("[连接关闭异常]", e);
            }
        }
    }

    public static String sendHttpsPostRequest(String url, String content,
                                              String charset, int connectTimeout, int readTimeout,
                                              boolean needCompress, String contentType, boolean returnSingle)
            throws BusinessException {
        BufferedReader in = null;
        HttpsURLConnection httpsConn = null;
        try {
            httpsConn = getHttpsURLConnection(url, connectTimeout, readTimeout,
                    contentType);
            if (StringUtils.isBlank(charset)) {
                charset = HttpParamDto.DEFAULT_CHARSET;
            }
            InputStream stream = postConnect(httpsConn, content, charset,
                    needCompress);

            in = new BufferedReader(new InputStreamReader(stream, charset));
            String result = getReturnResult(in, returnSingle);
            if (StringUtils.isBlank(result)) {
                throw new BusinessException("网络异常，" + url
                        + "无法联通");
            }
            return result;
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException("网络IO异常[" + url + "]", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.warn("", e);
            }
        }
    }

    public static String sendHttpsPostRequest(String url, String content,
                                              String charset) {
        return sendHttpsPostRequest(url, content, charset,
                HttpParamDto.DEFAULT_CONNECT_TIME_OUT,
                HttpParamDto.DEFAULT_READ_TIME_OUT, false, null, false);
    }

    private static InputStream postConnect(HttpURLConnection httpConn, String content,
                                           String charset, boolean needCompress) {
        String urlStr = httpConn.getURL().toString();
        try {
            if (StringUtils.isBlank(charset)) {
                charset = HttpParamDto.DEFAULT_CHARSET;
            }
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpConn.setDoOutput(true);
            // Post 请求不能使用缓存
            httpConn.setUseCaches(false);
            // 设定请求的方法为"POST"，默认是GET
            httpConn.setRequestMethod("POST");
            if (needCompress) {
                sendCompressRequest(content, charset, httpConn);
            } else {
                sendNoCompressRequest(content, charset, httpConn);
            }
            // 接收数据
            if (needCompress) {
                return new GZIPInputStream(httpConn.getInputStream());
            }
            return httpConn.getInputStream();
        } catch (MalformedURLException e) {
            log.warn("", e);
            throw new BusinessException(
                    "远程访问异常[" + urlStr + "]", e);
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException(
                    "网络IO异常[" + urlStr + "]", e);
        }
    }

    private static void sendCompressRequest(String content, String charset,
                                            HttpURLConnection httpConn) {
        GZIPOutputStream out = null;
        try {
            httpConn.setRequestProperty("Content-Type", "application/x-gzip");
            httpConn.setRequestProperty("Accept", "application/x-gzip");
            out = new GZIPOutputStream(httpConn.getOutputStream());
            out.write(content.getBytes("GBK"));
            out.flush();
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException("网络IO异常["
                    + httpConn.getURL().toString() + "]", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    }

    public static void main(String[] args) {

    }

    /**
     * 发送原始消息
     *
     * @param content
     * @param charset
     * @param httpConn
     */
    private static void sendNoCompressRequest(String content, String charset,
                                              HttpURLConnection httpConn) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new OutputStreamWriter(
                    httpConn.getOutputStream(), charset));
            out.write(content);
            out.flush();
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException("网络IO异常["
                    + httpConn.getURL().toString() + "]", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 建立远程连接
     *
     * @return
     */
    private static InputStream connect(HttpURLConnection httpConn) {
        String urlStr = httpConn.getURL().toString();
        try {
            if (httpConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.info(urlStr + "|ResponseCode="
                        + httpConn.getResponseCode());
                throw new BusinessException("远程访问" + urlStr
                        + "出错，返回结果为：" + httpConn.getResponseCode());
            }
            return httpConn.getInputStream();
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException(
                    "网络IO异常[" + urlStr + "]", e);
        }
    }

    /**
     * 构造URLConnnection
     *
     * @param urlStr
     * @param connectTimeout
     * @param readTimeout
     * @param contentType    content-type类型
     * @return
     * @throws BusinessException
     */
    private static HttpURLConnection getURLConnection(String urlStr,
                                                      int connectTimeout, int readTimeout, String contentType)
            throws BusinessException {
        try {
            URL remoteUrl = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) remoteUrl
                    .openConnection();
            httpConn.setConnectTimeout(connectTimeout);
            httpConn.setReadTimeout(readTimeout);
            if (contentType != null) {
                httpConn.setRequestProperty("content-type", contentType);
            }
            return httpConn;
        } catch (MalformedURLException e) {
            log.warn("", e);
            throw new BusinessException(
                    "远程访问异常[" + urlStr + "]", e);
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException(
                    "网络IO异常[" + urlStr + "]", e);
        }
    }

    /**
     * 构造URLConnnection
     *
     * @param urlStr
     * @param connectTimeout
     * @param readTimeout
     * @param contentType    content-type类型
     * @return
     * @throws BusinessException
     */
    private static HttpsURLConnection getHttpsURLConnection(String urlStr,
                                                            int connectTimeout, int readTimeout, String contentType)
            throws BusinessException {
        try {
            URL remoteUrl = new URL(urlStr);
            HttpsURLConnection httpConn = (HttpsURLConnection) remoteUrl
                    .openConnection();
            httpConn.setConnectTimeout(connectTimeout);
            httpConn.setReadTimeout(readTimeout);
            if (contentType != null) {
                httpConn.setRequestProperty("content-type", contentType);
            }
            return httpConn;
        } catch (MalformedURLException e) {
            log.warn("", e);
            throw new BusinessException(
                    "远程访问异常[" + urlStr + "]", e);
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException(
                    "网络IO异常[" + urlStr + "]", e);
        }
    }

    private static HttpURLConnection getURLConnection(String urlStr,
                                                      int connectTimeout, int readTimeout)
            throws BusinessException {
        return getURLConnection(urlStr, connectTimeout, readTimeout, null);
    }

    private static String getReturnResult(BufferedReader in, boolean returnSingleLine)
            throws IOException {
        if (returnSingleLine) {
            return in.readLine();
        } else {
            StringBuffer sb = new StringBuffer();
            String result = "";
            while ((result = in.readLine()) != null) {
//                log.debug("从中心返回：" + result);
                sb.append(StringUtils.trimToEmpty(result));
            }
            return sb.toString();
        }
    }

    public static String getRequestFileStr(String urlStr){
        BufferedReader in = null;
        HttpURLConnection conn = null;
        try {
            String charset = HttpParamDto.DEFAULT_CHARSET;
            conn = getURLConnection(urlStr, HttpParamDto.DEFAULT_CONNECT_TIME_OUT, HttpParamDto.DEFAULT_READ_TIME_OUT);
            in = new BufferedReader(new InputStreamReader(connect(conn),
                    charset));
            StringBuffer sb = new StringBuffer();
            String result = "";
            while ((result = in.readLine()) != null) {
                sb.append(StringUtils.trimToEmpty(result) + CommonConstant.SEPARATOR_LINE);
            }
//            if (StringUtils.isBlank(sb.toString())) {
//                throw new BusinessException("网络异常，" + urlStr
//                        + " 返回数据为空");
//            }
            return sb.toString();
        } catch (IOException e) {
            log.warn("", e);
            throw new BusinessException("网络IO异常[" + urlStr + "]", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.warn("", e);
            }
        }
    }
}
