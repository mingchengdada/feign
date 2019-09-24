package com.mc.kafkascript.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 项目名称:  PinkStone
 * 包:       com.migu.pinkstone.common.dto.response
 * 类名称:   BaseResponse
 * 类描述:   类功能详细描述
 * 创建人:   xiaosong
 * 创建时间:  2017 12-26 18:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    /**
     * 返回结果对象
     */
    @NotNull(message = "参数{name}不能为空")
    @Valid
    //result  (UCCESS_CODE,SUCCESS_MESSAGE)
    private Result result;
}
