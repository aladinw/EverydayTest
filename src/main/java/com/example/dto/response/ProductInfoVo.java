/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/25 17:49
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他商业用途。
 */
package com.example.dto.response;

import com.suixingpay.fin.floan.dubbo.rs.vo.BaseFloanRpcBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 产品详情
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/25 17:49
 * @version: V1.0
 * @review: li_pf[li_pf@suixingpay.com]/2018/8/25 17:49
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductInfoVo extends BaseFloanRpcBean {
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 产品编码
     */
    private String productNo;
    /**
     * 产品类型(01：信用卡代还;02：现金分期)
     */
    private String productType;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品描述
     */
    private String productDesc;

    /**
     * 生效标志(0：未发布;1：已发布;2：已作废)
     */
    private String effectFlag;

    /**
     * 单笔最低借款金额
     */
    private BigDecimal singleLimitLow;

    /**
     * 单笔最高借款金额
     */
    private BigDecimal singleAmtHigh;

    /**
     * 分期标志(Y：分期;N：不分期)
     */
    private String periodFlag;

    /**
     * 分期期数
     */
    private Integer periodCount;

    /**
     * 周期数值
     */
    private Integer periodLength;

    /***周期单位(D:天;M:月)*/
    private String periodUnit;

    /**
     * 还款方式(ONCE_REPAY:一次性还本付息;EQUAL_AMT_OF_INTEREST:等额本金;EQUAL_AMT_OF_PRINCIPAL:等额本息;EQUAL_PRINCIPAL_AND_INTEREST:等本等息)
     */
    private String repayType;

    /**
     * 适用渠道(MPOS_APP:MPOS_APP; XIN_APP:鑫联盟APP;随行付商户微信公众号:SXF_MERC_WX ;多渠道用逗号分隔)
     */
    private String sysChannel;

    /**
     * 实际最低可借金额
     */
    private BigDecimal factLimitLow;

    /**
     * 实际最高可借金额
     */
    private BigDecimal factAmtHigh;

    /**
     * 综合资金成本 年利率
     */
    private BigDecimal totalRate;
}
