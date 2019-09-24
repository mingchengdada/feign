package com.mc.kafkascript.DealKafkaRepair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.common.dto.model
 * 类名称:   PersonDeposit
 * 类描述:   账本充值对象
 * 创建人:    WU
 * 创建时间:  2019/2/15 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDeposit {


    String userId;


    String acctTypeCode;


    String scope;


    String depositCount;


    String billType;

    /**
     * 充值原因
     */

    String rechargeReason;

    /**
     * 生效时间
     */

    String effectiveDate;

    /**
     * 失效时间
     */

    String expiryDate;

    /**
     * 内部编码
     */

    String innerId;

    /**
     * 外部编码
     */

    String externalId;

    /**
     * 电影卡批次号
     */

    String cardBatchId;

    /**
     * 是否多账本
     */

    Integer isMulti;

    /**
     * 是否可延期
     */

    Integer isDefer;

    /**
     * 来源
     */

    String channelId;

    /**
     * 营销活动编码
     */

    String activityId;

    /**
     * 营销活动名称
     */

    String activityName;

    /**
     * 扩展字段
     */

    String extInfo;

    //操作来源

    String opCode;
}
