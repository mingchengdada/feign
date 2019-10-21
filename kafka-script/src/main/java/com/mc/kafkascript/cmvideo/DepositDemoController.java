//package com.mc.kafkascript.cmvideo;
//
//import com.alibaba.fastjson.JSON;
//import com.mc.kafkascript.cmvideo.dto.DepositPersonalBookReq;
//import com.mc.kafkascript.cmvideo.dto.DepositPersonalBookResp;
//import com.mc.kafkascript.cmvideo.dto.PersonDeposit;
//import com.mc.kafkascript.cmvideo.dto.RequestHeader;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
///**
// * 项目名称:   pinkstone
// * 包:        com.mc.kafkascript.cmvideo
// * 类名称:     DepositDemoController
// * 类描述:     类功能描述
// * 创建人:     mc
// * 创建时间:   2019/10/12 6:42
// */
//@RestController
//@Slf4j
//public class DepositDemoController {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @GetMapping("/deposit")
//    public boolean deposit(HttpServletRequest req) {
//        for (int i = 0; i < 100000; i++) {
//            DepositPersonalBookReq depositPersonalBookReq = new DepositPersonalBookReq();
//            String depositHeader = "{\n" +
//                    "    \"version\":\"1.0\",\n" +
//                    "    \"transactionId\":\"11111111111111111\",\n" +
//                    "    \"productLine\":\"50\",\n" +
//                    "    \"portalType\":\"50\",\n" +
//                    "    \"companyId\":\"50\",\n" +
//                    "    \"platform\":\"50\"\n" +
//                    "}";
//            RequestHeader deRequestHeader = JSON.parseObject(depositHeader, RequestHeader.class);
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//            String date = simpleDateFormat.format(calendar.getTime());
//
//            calendar.add(Calendar.MONTH, 1);
//            String afterMothDay = simpleDateFormat.format(calendar.getTime());
//
//
//            PersonDeposit personDeposit = new PersonDeposit();
//            personDeposit.setUserId(userId);
//            personDeposit.setAcctTypeCode("CMVIDEO_TICKET");
//            personDeposit.setScope("");
//            personDeposit.setDepositCount(amount);
//            personDeposit.setBillType("1");
//            personDeposit.setRechargeReason("用户投诉补充通看券".concat(date));
//            personDeposit.setEffectiveDate("");
//            personDeposit.setExpiryDate(afterMothDay.substring(0, 8) + "235959");
//            personDeposit.setInnerId("complaint_handle_" + userId);
//            personDeposit.setExternalId("");
//            personDeposit.setCardBatchId("");
//            personDeposit.setIsMulti(null);
//            personDeposit.setIsDefer(null);
//            personDeposit.setChannelId(videoType);
//            personDeposit.setActivityId("");
//            personDeposit.setActivityName("");
//            personDeposit.setExtInfo("type=" + videoType);
//            personDeposit.setOpCode("");
//            depositPersonalBookReq.setRequestHeader(deRequestHeader);
//            depositPersonalBookReq.setDepositDetail(personDeposit);
//
//            ResponseEntity<DepositPersonalBookResp> resp = restTemplate
//                    .postForEntity("http://10.181.0.97:30008/pinkstone-personalbook/account/depositPersonalBook"
//                            , depositPersonalBookReq, DepositPersonalBookResp.class);
//        }
//}
