package com.mc.kafkascript.DealKafkaRepair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 接口消息头 项目名称: common.
 * 包: com.migu.purplestone.dto
 * 类名称: RequestHeader
 * 类描述: 类功能详细描述
 * 创建人: wubei
 * 创建时间: 2017 下午2:27:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestHeader {


    private String version;


    private String transactionId;


    private String productLine;


    private String portalType;


    private String companyId;

    private String platform;

    private String imei;

    private String imsi;


    private String clientVer;


    private String appid;


    private String appName;


    private String accessInfo;


    private String extention;
}
