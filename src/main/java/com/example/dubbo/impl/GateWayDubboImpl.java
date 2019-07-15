package com.example.dubbo.impl;


import com.example.dto.BaseGWRpcBean;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWRpcResponse;
import com.example.dubbo.GateWayDubbo;
import com.example.route.BaseRoute;
import com.example.route.impl.DubboRouteImpl;

public class GateWayDubboImpl implements GateWayDubbo {
    @Override
    public GWRpcResponse<BaseGWRpcBean> dubboInvoke(GWRpcRequest<BaseGWRpcBean> rpcRequest) {

        BaseRoute<GWRpcResponse> baseRoute = new DubboRouteImpl();
        GWRpcResponse<BaseGWRpcBean> gwRpcResponse = baseRoute.doRoute(rpcRequest);
        return gwRpcResponse;

    }
}
