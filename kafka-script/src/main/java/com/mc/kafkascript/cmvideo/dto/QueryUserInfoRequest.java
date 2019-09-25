package com.mc.kafkascript.cmvideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 项目名称：pinkstone
 * 包：com.migu.pinkstone.dto
 * 类名称：QueryUserInfoRequest
 * 类描述：描述该类的功能
 * 创建人：Administrator
 * 创建时间：2017-9-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryUserInfoRequest {

    /**
     * 报文头信息.
     */
    private RequestHeader requestHeader;

    /**
     * 用户标识
     */
    private String userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户号码
     */
    private String userAccountType;

    /**
     * 数据来源
     */
    private String dataSource;
}
