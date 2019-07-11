/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 13:58
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他商业用途。
 */
package com.example.dto.request;

import com.example.dto.BaseGWRpcBean;
import com.suixingpay.fin.floan.dubbo.rs.vo.BaseFloanRpcBean;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * 借款请求参数封装
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 13:58
 * @version: V1.0
 * @review: li_pf[li_pf@suixingpay.com]/2018/8/13 13:58
 */
@Data
@ToString
public class GWRpcRequest<T extends BaseGWRpcBean> implements Serializable {

    private String requestId_gw;
    private String sysId_gw;

    private String serviceName_gw;

    private String businesscode_gw;

    private T data_gw;

    public GWRpcRequest() {
    }

    public GWRpcRequest(String requestId_gw, String sysId_gw, T data_gw) {
        this.requestId_gw = requestId_gw;
        this.sysId_gw = sysId_gw;
        this.data_gw = data_gw;
    }

    public GWRpcRequest(String sysId_gw, T data) {
        this(UUID.randomUUID().toString().replaceAll("-", ""), sysId_gw, data);
    }

}
