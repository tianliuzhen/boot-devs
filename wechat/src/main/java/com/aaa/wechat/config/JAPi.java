package com.aaa.wechat.config;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * @author liuzhen.tian
 * @version 1.0 JAPi.java  2020/8/22 17:19
 */
public class JAPi {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("F:\\WorkSpace\\MyGithub\\boot-devs\\wechat");
        // 项目名称
        config.setProjectName("wechat");
        // 声明该API的版本
        config.setApiVersion("V1.0");
        // 生成API 文档所在目录
        config.setDocsPath("your api docs path");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
