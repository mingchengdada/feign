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
 * 创建时间:  2019/10/12 10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NpInfoResult {
    private String success;
    private String resultCode;
    private String resultMessage;
    private DetailNpInfo detailNpInfo;

}
