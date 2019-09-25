package com.mc.kafkascript.cmvideo.dto;

import lombok.*;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.common.dto.response
 * 类名称:   DepositPersonalBookResp
 * 类描述:   用户重置response
 * 创建人:    WU
 * 创建时间:  2019/2/15 14:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DepositPersonalBookResp extends BaseResponse{

    /**
     * 交易流水
     */
    private TransactionFlow transactionFlow;
}
