/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 13:58
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他商业用途。
 */
package com.example.dto.response;

import com.example.dto.BaseGWRpcBean;
import com.suixingpay.fin.floan.dubbo.rs.vo.BaseFloanRpcBean;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 借款响应数据封装
 *
 * @author: li_pf[li_pf@suixingpay.com]
 * @date: 2018/8/13 13:58
 * @version: V1.0
 * @review: li_pf[li_pf@suixingpay.com]/2018/8/13 13:58
 */
@Data
@ToString
public class GWRpcResponse<T extends BaseGWRpcBean> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6288681082511735243L;
    private String requestId_gw;
    private String sysId_gw;
    private boolean bSuccess_gw = true;
    private String code_gw = "0000";
    private String message_gw = "请求处理成功";
    private T data_gw;

    public GWRpcResponse() {
    }

    public GWRpcResponse(T data_gw) {
        this.data_gw = data_gw;
    }
}
