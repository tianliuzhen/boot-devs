package com.aaa.wechat.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liuzhen.tian
 * @version 1.0 IOUtil.java  2021/5/16 17:27
 */
public class IOUtil {

    /**
     * InputStream 转 字节
     *
     * @param inputStream 输入流
     * @return byte[]
     * @throws IOException
     */
    public static byte[] read(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
