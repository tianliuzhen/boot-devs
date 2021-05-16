package com.aaa.wechat.web.base;

import com.aaa.wechat.utils.IOUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 参考：https://blog.csdn.net/liuyueyi25/article/details/79102988
 * 一般来说，一个后端提供的服务接口，往往是返回json数据的居多，前面提到了直接返回图片的场景，那么常见的返回图片有哪些方式呢？
 * <p>
 * 返回图片的http地址
 * 返回base64格式的图片
 * 直接返回二进制的图片
 * 其他…（我就见过上面三种，别的还真不知道）
 *
 * @author liuzhen.tian
 * @version 1.0 ImgController.java  2021/5/16 16:31
 */

@RestController
@RequestMapping(value = "/wx-img")
public class ImgController {


    /**
     * 返回二进制图片
     *
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/getQRCode")
    @ResponseBody
    public String getQRCode(HttpServletResponse response) throws IOException {
        // 随便找个图片地址
        String urlStr = "https://file.service.qq.com/user-files/uploads/201901/f5c1fa7e09d8ff42a6bbb02c05bf971b.jpg";

        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5 * 1000);
            connection.connect();
            inputStream = connection.getInputStream();
            // img为图片的二进制流
            byte[] img = IOUtil.read(inputStream);

            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            os.write(img);
            os.flush();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            connection.disconnect();
        }

        return "success";
    }




}
