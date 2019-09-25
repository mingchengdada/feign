package com.mc.kafkascript.cmvideo.dto;

import lombok.Data;

import java.util.Date;

/**
 * 查询用户信息返参实体类
 * 
 * @author zhuo_
 *
 */
@Data
public class UserInfoResponse {
	private String userId;
	
	private String originalUserId;

	private String userAccount;

	private String userAccountType;

	private String msisdn;

	private Date createTime;

	private String passId;

	private String provinceCode;

	private String provinceName;

	private String cityCode;

	private String cityName;

	private String ImsiType;
}
