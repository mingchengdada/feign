package com.mc.kafkascript.feign;

import com.mc.kafkascript.testcontroller.QueryNpInfoReq;
import com.mc.kafkascript.testcontroller.QueryNpInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.TimeoutException;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.api
 * 类名称:   INpInfoApi
 * 类描述:   调用大网 npInfo 接口
 * 创建人:    xubx
 */
@FeignClient(name = "test",url = "http://112.25.126.85:18080",fallback = NpInfoFallBack.class
        ,configuration = FeignHttpClientProxyConf.class)
public interface INpInfoApi {
    @RequestMapping(value = "/cmbs/query/npInfo",method = RequestMethod.POST,produces
            = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    QueryNpInfoResp queryNpInfo(@RequestBody @Validated QueryNpInfoReq request) throws TimeoutException;
}
