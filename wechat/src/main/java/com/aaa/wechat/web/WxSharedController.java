package com.aaa.wechat.web;

import com.aaa.wechat.config.WxJxPayProperties;
import com.aaa.wechat.utils.IOUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 获取小程序码
 *
 * @author liuzhen.tian
 * @version 1.0 WxSharedController.java  2021/5/16 16:59
 */
public class WxSharedController {

    @Autowired
    private WxJxPayProperties siteConfig;


    /**
     * 返回分享小程序二维码
     * 参考官网：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
     * 该接口有三个，可根据自己选择需要的接口，
     * 下面这个接口是（永久有效，数量暂无限制）
     * 步骤：
     * 1、获取 access_token
     * 2、获取二维码接口
     * 3、返回
     */
    @GetMapping(value = "/getQRCode")
    @ResponseBody
    public String getQRCode(HttpServletResponse response,
                            @RequestParam(required = false, defaultValue = "") String page) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String wxCodeURL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + getToken();

        JSONObject param = new JSONObject();
        // 场景值默认不能为空
        param.put("scene", "");
        // 跳转路径
        param.put("page", page);
        HttpPost httpPost = new HttpPost(wxCodeURL);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity se = new StringEntity(JSON.toJSONString(param));
        httpPost.setEntity(se);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                // 将图片保存在本次磁盘D盘，命名为xxx.png
                // FileUtils.copyToFile(inputStream, new File("D://wx.png"));

                // case1、返回base64
                // return "data:image/png;base64," + Base64.getEncoder().encodeToString(read(inputStream));

                // case2、返回二进制图片
                ImgStream(response, inputStream);
            }

        }
        return "success";
    }

    //
    private void ImgStream(HttpServletResponse response, InputStream inputStream) throws IOException {
        byte[] img = IOUtil.read(inputStream);
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        os.write(img);
        os.flush();
        os.close();
    }



    public String getToken() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String authUrl = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential" +
                "&appid=" + siteConfig.getAppId() +
                "&secret=" + siteConfig.getSecret();
        HttpGet authHttp = new HttpGet(authUrl);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(authHttp);
            if (response.getStatusLine().getStatusCode() == 200) {
                String res = EntityUtils.toString(response.getEntity(), "UTF-8").replaceAll("[ \n\t\r]", "");
                JSONObject jsonResp = JSONObject.parseObject(res);
                return jsonResp.getString("access_token");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        throw new IllegalArgumentException("获取token异常");
    }

}
