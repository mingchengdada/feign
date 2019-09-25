package com.mc.kafkascript.cmvideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

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

    /**
     * 用户编码
     */
    @NotBlank(message = "参数{name}不能为空")
    @Length(max = 128, message = "参数{name}长度超长")
    String userId;

    /**
     * 账本类型Code
     */
    @NotBlank(message = "参数{name}不能为空")
    @Length(max = 64, message = "参数{name}长度超长")
    String acctTypeCode;

    /**
     * 卡分类
     */
    @Length(max = 32, message = "参数{name}长度超长")
    String scope;

    /**
     * 充值金额/数量
     */
    @NotBlank(message = "参数{name}不能为空")
    String depositCount;

    /**
     * 账单类型：
     * 1 充值
     * 3 补款
     * 6 账本迁移
     * 8 充值赊账
     */
    @Length(max = 32, message = "参数{name}长度超长")
    @NotBlank(message = "参数{name}不能为空")
    String billType;

    /**
     * 充值原因
     */
    @Length(max = 256, message = "参数{name}长度超长")
    String rechargeReason;

    /**
     * 生效时间
     */
    @Pattern(regexp = "^\\s*$|([1-9]\\d{3}((0[1-9]|1[012])(0[1-9]|1\\d|2[0-8])|(0[13456789]|1[012])(29|30)|"
            +  "(0[13578]|1[02])31)|(([2-9]\\d)(0[48]|[2468][048]|[13579][26])|(([2468][048]|"
            + "[3579][26])00))0229)([0-1]{1}\\d{1}|[2]{1}[0-3]{1})(?::)?([0-5]{1}\\d{1})(?::)?([0-5]{1}\\d{1})",
            message = "参数{name}格式不正确")
    String effectiveDate;

    /**
     * 失效时间
     */
    @Pattern(regexp = "^\\s*$|([1-9]\\d{3}((0[1-9]|1[012])(0[1-9]|1\\d|2[0-8])|(0[13456789]|1[012])(29|30)|"
            +  "(0[13578]|1[02])31)|(([2-9]\\d)(0[48]|[2468][048]|[13579][26])|(([2468][048]|"
            + "[3579][26])00))0229)([0-1]{1}\\d{1}|[2]{1}[0-3]{1})(?::)?([0-5]{1}\\d{1})(?::)?([0-5]{1}\\d{1})",
            message = "参数{name}格式不正确")
    String expiryDate;

    /**
     * 内部编码
     */
    @Length(max = 128, message = "参数{name}长度超长")
    String innerId;

    /**
     * 外部编码
     */
    @Length(max = 128, message = "参数{name}长度超长")
    String externalId;

    /**
     * 电影卡批次号
     */
    @Length(max = 32, message = "参数{name}长度超长")
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
    @Length(max = 32, message = "参数{name}长度超长")
    String channelId;

    /**
     * 营销活动编码
     */
    @Length(max = 128, message = "参数{name}长度超长")
    String activityId;

    /**
     * 营销活动名称
     */
    @Length(max = 128, message = "参数{name}长度超长")
    String activityName;

    /**
     * 扩展字段
     */
    @Length(max = 1024, message = "参数{name}长度超长")
    String extInfo;

    //操作来源
    @Length(max = 32, message = "参数{name}长度超长")
    String opCode;
}
