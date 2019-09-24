package com.mc.kafkascript.DealKafkaRepair;

import com.alibaba.fastjson.JSON;
import com.mc.kafkascript.feign.Deposit;
import com.mc.kafkascript.feign.DepositPersonalBookResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.DealKafkaRepair
 * 类名称:     ReTransferDeposit
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/23 17:05
 */
@RestController
@Slf4j
public class ReTransferDeposit {
    @Autowired
    private Deposit deposit;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET,value = "/reSend")
    public boolean hand() throws IOException {
        BufferedReader fileInputStream = new BufferedReader(new FileReader(new File(System
                .getProperty("user.dir")+"/reSend.txt")));
        //直接是入参
        String msg;
        while((msg = fileInputStream.readLine()) != null) {
            try {
                DepositPersonalBookReq req = JSON.parseObject(msg, DepositPersonalBookReq.class);
                DepositPersonalBookResp resp =  deposit.deposit(req);
                log.error("---重发正常---,resp is {}",resp);
            } catch (Exception e) {
                log.error("---重发异常---,error is {}",e);
            }
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/reSend1")
    public boolean hand1() throws IOException {
        BufferedReader fileInputStream = new BufferedReader(new FileReader(new File(System
                .getProperty("user.dir")+"/reSend.txt")));
        //直接是入参
        String msg;
        while((msg = fileInputStream.readLine()) != null) {
            try {
                DepositPersonalBookReq req = JSON.parseObject(msg, DepositPersonalBookReq.class);
                ResponseEntity<DepositPersonalBookResp> response = restTemplate
                        .postForEntity("uti",req,DepositPersonalBookResp.class);
                log.error("---重发正常---,resp is {}",response.getBody());
            } catch (Exception e) {
                log.error("---重发异常---,error is {}",e);
            }
        }
        return true;
    }
}
