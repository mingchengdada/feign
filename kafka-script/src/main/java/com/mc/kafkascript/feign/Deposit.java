package com.mc.kafkascript.feign;

import com.mc.kafkascript.DealKafkaRepair.DepositPersonalBookReq;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.feign
 * 类名称:     Deposit
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/23 17:09
 */
// @FeignClient(name = "deposit",configuration = FeignConfig.class,url = "http://10.181.0.97:30008/pinkstone-personalbook")
@FeignClient(name = "deposit",configuration = FeignConfig.class,url = "http://10.21.19.82:8523")

public interface Deposit {
        @RequestMapping(value = "/account/depositPersonalBook",method = RequestMethod.POST,
                produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
        DepositPersonalBookResp deposit(@RequestBody DepositPersonalBookReq req);
}
