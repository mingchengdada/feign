package com.mc.kafkascript.cmvideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称：pinkstone
 * 包：com.migu.pinkstone.dto
 * 类名称：QueryUserInfoResponseFor
 * 类描述：描述该类的功能
 * 创建人：Administrator
 * 创建时间：2017-9-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryUserInfoResponseFor {
	 /**
     * 返回结果对象
     */
    private Result result;

    /**
     * 返回用户信息列表
     */
    private UserInfoResponse userInfo;
}
