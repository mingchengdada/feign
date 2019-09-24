package com.mc.kafkascript.feign;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 项目名称:  pinkstone.
 * 包:        com.migu.pinkstone.common.entity
 * 类名称:    Result
 * 类描述:    结果对象
 * 创建人:    limeng6
 * 创建时间:   2017/12/5 10:16
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {
    /**
     * .
     */
    private String resultCode;
        /**
     * .
     */
    private String resultMessage;
    /**
     * .
     */
    /**
     * .
     */

    /**
     * .
     */
    public Result(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
    /**
     * .
     * @param isSuccess
     */
    public Result(boolean isSuccess) {
        if (isSuccess) {
            this.resultCode = "1600000000";
            this.resultMessage = "成功";
        }
    }
}
