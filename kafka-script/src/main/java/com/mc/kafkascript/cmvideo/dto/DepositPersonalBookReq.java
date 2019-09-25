package com.mc.kafkascript.cmvideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.common.dto.request
 * 类名称:   DepositPersonalBookReq
 * 类描述:   用户重置request
 * 创建人:    WU
 * 创建时间:  2019/2/15 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositPersonalBookReq {
    /**
     * 充值对象
     */
    @NotNull(message = "参数{name}不能为空")
    @Valid
    private PersonDeposit depositDetail;

    /**
     * 请求头
     */
    @Valid
    @NotNull(message = "参数{name}不能为空")
    private RequestHeader requestHeader;
}
