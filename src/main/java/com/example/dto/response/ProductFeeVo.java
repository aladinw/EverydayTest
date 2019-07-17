/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 14:29
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他商业用途。
 */
package com.example.dto.response;

import com.example.dto.BaseGWRpcBean;
import com.suixingpay.fin.floan.dubbo.rs.vo.BaseFloanRpcBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 产品费用 VO
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 14:29
 * @version: V1.0
 * @review: li_pf[li_pf@suixingpay.com]/2018/8/13 14:29
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductFeeVo extends BaseGWRpcBean {
    /** 收取方式(固定金额:FIXED_AMT;按天比例:DAY_RATE;按月比例:MONTH_RATE;按年比例:YEAR_RATE;单笔比例:ONE_RATE) */
    private  String  feeChargeType;
    /** 收取最低值 */
    private BigDecimal feeLowAmt;
    /** 收取最高值 */
    private  BigDecimal  feeHighAmt;
    /** 费用利率 */
    private  BigDecimal  feeRate;
    /** 有效起始时间(YYYYMMDD) */
    private  String  effectStartDate;
    /** 有效截止时间(YYYYMMDD) */
    private  String  effectEndDate;
    /** 生效状态(0：未发布;1：已发布;2：已作废) */
    private  String  effectFlag;
    /** 创建柜员 */
    private  String  createOperatorId;
    /** 创建日期 */
    private  String  createDate;
    /** 创建时间 */
    private  String  createTime;
    /** 修改柜员 */
    private  String  updateOperateId;
    /** 修改日期 */
    private  String  updateDate;
    /** 修改时间 */
    private  String  updateTime;
    /** 时间戳 */
    private  String  timeStamp;
    /** 时间戳 */
    private  String  productFeeId;
    /** 产品ID */
    private  String  productId;
    /** 产品编码 */
    private  String  productNo;
    /** 费用类型(利息:INTEREST_FEE;服务费:SERVICE_FEE;滞纳金费用:LATE_FEE;逾期费用:OVERDUE_FEE;其他费用:OTHER_FEE) */
    private  String  feeType;
    /** 费用名称 */
    private  String  feeName;
    /** 收费对象(基于应还金额:RE_AMT;基于借款本金:LOAN_PRINCIPAL;PERD_RE_AMT基于分期应还金额;PERD_LOAN_PRINCIPAL基于分期借款本金) */
    private  String  feeObject;
    /**
     * 还款方式:
     *          ONCE_REPAY:一次性还本付息;
     *          EQUAL_AMT_OF_INTEREST:等额本金;
     *          EQUAL_AMT_OF_PRINCIPAL:等额本息;
     *          EQUAL_PRINCIPAL_AND_INTEREST:等本等息
     */
    private String feeRepayType;
}
