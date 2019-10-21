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
 * 创建时间:  2019/10/12 10:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailNpInfo {
    private String serviceNumber;
    private String state;
    private String stateDesc;
    private String netId;
    private String netIdDesc;
    private String portInId;
    private String portInIdDesc;
    private String portOutId;
    private String portOutIdDesc;
    private String homeNet;
    private String homeNetDesc;
    private String activeTime;
    private String inactiveTime;
    private String serviceType;
    private String serviceTypeDesc;
    private String region;
    private String regionName;
    private String province;
    private String provinceName;

}
