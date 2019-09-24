package com.mc.kafkascript.feign;

import lombok.Data;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.common.dto.model
 * 类名称:   pinkstone10
 * 类描述:   交易流水
 * 创建人:    WU
 * 创建时间:  2019/2/15 14:57
 */
@Data
public class TransactionFlow {

    /**
     * 账单编号
     */
    String billFlowId;

    /**
     * 用户编码
     */
    String userId;

    /**
     * 账本编码
     */
    String acctBookId;

    /**
     * 账本类型Code
     */
    String acctTypeCode;

    /**
     * 卡分类
     */
    String socpe;

    /**
     * 账单类型:
     * 1 充值
     * 2 直接扣款
     * 3 补款
     * 4 预留扣款
     * 5 回收账本
     * 6 账本迁移
     * 7 核减流水
     * 8 充值赊账
     * 0 确认扣款
     */
    String billType;

    /**
     * 外部交易编号
     */
    String externalId;

    /**
     * 内部流水
     */
    String innerId;

    /**
     * 余额
     */
    String balance;

    /**
     * （充值/消费）金额/数量
     */
    String amount;

    /**
     * 操作时间
     */
    String operateTime;

    /**
     * 元数据(账本细类元数据)
     */
    String metaInfo;

    String acctExpiryTime;

    /*
    * 充值/消费原因[流水remark字段]
    * */
    String billReason;
}
