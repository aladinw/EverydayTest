package com.example.dubbo;

import com.example.dto.BaseGWRpcBean;
import com.example.dto.request.GWProductVo;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWProductRespVo;
import com.example.dto.response.GWRpcResponse;

public interface GateWayDubbo {

    GWRpcResponse<BaseGWRpcBean> dubboInvoke(GWRpcRequest<BaseGWRpcBean> gw);
}
