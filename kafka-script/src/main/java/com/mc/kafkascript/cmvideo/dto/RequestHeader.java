package com.mc.kafkascript.cmvideo.dto;

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

    /**
     * 协议版本.
     */
    private String version;

    /**
     * 交易流水号.
     */
    private String transactionId;

    /**
     * 产品线编码.
     */
    private String productLine;

    /**
     * 接入类型编码.
     */
    private String portalType;

    /**
     * 厂商ID.
     */
    private String companyId;

    /**
     * 平台类型.
     */
    private String platform;

    /**
     * 客户端版本号.
     */
    private String clientVer;

    /**
     * 应用ID.
     */
    private String appid;

    /**
     * 应用名称.
     */
    private String appName;

    /**
     * 用户访问线索.
     */
    private String accessInfo;

    /**
     * 灰度使用.
     */
    private String extention;
}
