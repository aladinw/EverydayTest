/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 14:27
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他商业用途。
 */
package com.example.dto.response;

import com.suixingpay.fin.floan.dubbo.rs.vo.BaseFloanRpcBean;
import com.suixingpay.fin.floan.dubbo.rs.vo.respVo.ProductInfoVo;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 产品详情VO
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 14:27
 * @version: V1.0
 * @review: li_pf[li_pf@suixingpay.com]/2018/8/13 14:27
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class GWProductRespVo extends BaseFloanRpcBean {
    private List<ProductFeeVo> productFeeList_gw;
    private List<ProductInfoVo> productInfoList_gw;

    public List<ProductFeeVo> getProductFeeList_gw() {
        return productFeeList_gw;
    }

    public void setProductFeeList_gw(List<ProductFeeVo> productFeeList_gw) {
        this.productFeeList_gw = productFeeList_gw;
    }

    public List<ProductInfoVo> getProductInfoList_gw() {
        return productInfoList_gw;
    }

    public void setProductInfoList_gw(List<ProductInfoVo> productInfoList_gw) {
        this.productInfoList_gw = productInfoList_gw;
    }
}
