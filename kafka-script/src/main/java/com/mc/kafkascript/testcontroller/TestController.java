package com.mc.kafkascript.testcontroller;

import com.alibaba.fastjson.JSON;
import com.mc.kafkascript.feign.INpInfoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.testcontroller
 * 类名称:     TestController
 * 类描述:     测试临时用户中心大网接口调用是否成功
 * 创建人:     mc
 * 创建时间:   2019/10/15 18:17
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private INpInfoApi iNpInfoApi;

    @GetMapping("/test")
    public boolean isOk(HttpServletRequest request) {
        String msisdn = request.getParameter("msisdn");
        QueryNpInfoReq req = new QueryNpInfoReq();
        Header header = new Header();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(new Date());
        header.setVersion("1.0");
        header.setSourceDeviceCode("cmbsMiguUserCenterTest");
        header.setAuthenticatorSource("447a26079d733e90d3c49f98b8d8c2d11d8cdf6f51a3d274c0c253d9142b3fd8");
        header.setTransactionId("1111");
        header.setTimeStamp(timeStamp);
        header.setPortalType("04");
        header.setPlatform("72");
        header.setProductLine("72");
        header.setCompanyId("17");
        req.setRequestHeader(header);
        String[] serviceNumberList = new String[1];
        serviceNumberList[0] = msisdn;
        req.setServiceNumberList(serviceNumberList);
        req.setShopCode("172");
        QueryNpInfoResp resp;
        try {
            /*ResponseEntity<QueryNpInfoResp> resp = restTemplate
                    .postForEntity("http://10.181.8.22:18080/cmbs/query/npInfo", req, QueryNpInfoResp.class);*/
            log.error("大王接口入参:{}", JSON.toJSONString(req));
            resp = iNpInfoApi.queryNpInfo(req);
            log.error("大王接口入参:{},返回:{}", req, resp);
        } catch (Exception e) {
            log.error("大王借口调用异常:{}",e.toString());
        }
        return true;
    }
}
