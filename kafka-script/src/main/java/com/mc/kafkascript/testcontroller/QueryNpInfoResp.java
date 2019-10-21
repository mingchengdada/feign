package com.mc.kafkascript.testcontroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.dto
 * 类名称:   temp_secuser
 * 类描述:   类功能详细描述
 * 创建人:    xubx
 * 创建时间:  2019/10/12 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryNpInfoResp {
    private Result result;
    private String bizCode;
    private String upResultCode;
    private String upResultMsg;
    private String queryCount;
    private NpInfoResult[] npInfoResultList;
}
