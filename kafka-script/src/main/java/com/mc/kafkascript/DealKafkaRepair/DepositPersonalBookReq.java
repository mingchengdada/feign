package com.mc.kafkascript.DealKafkaRepair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private PersonDeposit depositDetail;

    /**
     * 请求头
     */
    private RequestHeader requestHeader;
}
