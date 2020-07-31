package com.aaa.sass.config.configGlobalResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoException  extends RuntimeException {
    private String errorMsg;

    private int errorCode;
}


                /**
                 *  spring  利用 @ResponseAdvice  和 ResponseBodyAdvice
                 *  处理返回值和异常
                 *  在 dev-1.0.0 分支
                 */
