package com.mc.kafkascript.cmvideo;

import com.alibaba.fastjson.JSON;
import com.mc.kafkascript.cmvideo.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.cmvideo
 * 类名称:     VideoDoposit
 * 类描述:     通看券充值
 * 创建人:     mc
 * 创建时间:   2019/9/25 14:34
 */
@RestController
@Slf4j
public class VideoDopositController {
    // 通看券充值一般提供的类型:手机号,type,充值类型

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/video/deposit")
    public boolean deposit(HttpServletRequest req) {
        String phone = req.getParameter("phone");
        String videoType = req.getParameter("type");
        String amount = req.getParameter("amount");

        // 固定header
        String header = "{\n" +
                "    \"version\":\"1\",\n" +
                "    \"transactionId\":\"2019081000222260550\",\n" +
                "    \"productLine\":\"52\",\n" +
                "    \"portalType\":\"05\",\n" +
                "    \"companyId\":\"10\",\n" +
                "    \"platform\":\"01\",\n" +
                "    \"imei\":\"1233456\",\n" +
                "    \"imsi\":\"111111\",\n" +
                "    \"clientVer\":\"123\",\n" +
                "    \"appid\":\"migu\",\n" +
                "    \"appName\":\"1111\"\n" +
                "}";
        RequestHeader requestHeader = JSON.parseObject(header,RequestHeader.class);
        QueryUserInfoRequest queryUserInfoRequest = new QueryUserInfoRequest();
        queryUserInfoRequest.setRequestHeader(requestHeader);

        queryUserInfoRequest.setDataSource("0");
        queryUserInfoRequest.setUserAccountType("0");
        queryUserInfoRequest.setUserAccount(phone);
        queryUserInfoRequest.setUserId("");

        //调用临时用户中心查询userId
        ResponseEntity<QueryUserInfoResponseFor> userInfoResponse = restTemplate
                .postForEntity("http://10.181.0.21:31237/user/queryUserInfo",queryUserInfoRequest
                        ,QueryUserInfoResponseFor.class);
        if(userInfoResponse == null && userInfoResponse.getBody() == null) {
            return false;
        }
        log.error("视讯侧用户信息:{}",userInfoResponse.getBody());
        String userId =  userInfoResponse.getBody().getUserInfo().getUserId();
        // 加子公司编码
        userId = userId.startsWith("013U") ? userId : "013U" + userId;

        log.error("用户手机号:{},userId:{}",phone,userId);
        DepositPersonalBookReq depositPersonalBookReq = new DepositPersonalBookReq();
        String depositHeader = "{\n" +
                "    \"version\":\"1.0\",\n" +
                "    \"transactionId\":\"11111111111111111\",\n" +
                "    \"productLine\":\"50\",\n" +
                "    \"portalType\":\"50\",\n" +
                "    \"companyId\":\"50\",\n" +
                "    \"platform\":\"50\"\n" +
                "}";
        RequestHeader deRequestHeader = JSON.parseObject(depositHeader,RequestHeader.class);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(calendar.getTime());

        calendar.add(Calendar.MONTH,1);
        String afterMothDay = simpleDateFormat.format(calendar.getTime());


        PersonDeposit personDeposit = new PersonDeposit();
        personDeposit.setUserId(userId);
        personDeposit.setAcctTypeCode("CMVIDEO_TICKET");
        personDeposit.setScope("");
        personDeposit.setDepositCount(amount);
        personDeposit.setBillType("1");
        personDeposit.setRechargeReason("用户投诉补充通看券".concat(date));
        personDeposit.setEffectiveDate("");
        personDeposit.setExpiryDate(afterMothDay);
        personDeposit.setInnerId("complaint_handle_"+userId);
        personDeposit.setExternalId("");
        personDeposit.setCardBatchId("");
        personDeposit.setIsMulti(null);
        personDeposit.setIsDefer(null);
        personDeposit.setChannelId(videoType);
        personDeposit.setActivityId("");
        personDeposit.setActivityName("");
        personDeposit.setExtInfo("type="+videoType);
        personDeposit.setOpCode("");
        depositPersonalBookReq.setRequestHeader(deRequestHeader);
        depositPersonalBookReq.setDepositDetail(personDeposit);

        ResponseEntity<DepositPersonalBookResp> resp = restTemplate
                .postForEntity("http://10.181.0.97:30008/pinkstone-personalbook/account/depositPersonalBook"
                ,depositPersonalBookReq,DepositPersonalBookResp.class);

        if(userInfoResponse == null && userInfoResponse.getBody() == null) {
            return false;
        }
        TransactionFlow transactionFlow = resp.getBody().getTransactionFlow();
        log.error("充值通看券成功，充值流水:{},返回码:{}",JSON.toJSONString(transactionFlow),userInfoResponse
                .getBody().getResult().getResultCode());
        return true;
    }
}
