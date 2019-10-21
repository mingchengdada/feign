package com.mc.kafkascript.feign;

import com.mc.kafkascript.testcontroller.QueryNpInfoReq;
import com.mc.kafkascript.testcontroller.QueryNpInfoResp;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

/**
 * 项目名称:   pinkstone
 * 包:        com.migu.pinkstone.outClient
 * 类名称:     NpInfoFallBack
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/10/15 15:12
 */
@Component
public class NpInfoFallBack implements INpInfoApi {
    @Override
    public QueryNpInfoResp queryNpInfo(QueryNpInfoReq request) throws TimeoutException {
        throw new TimeoutException();
    }
}
