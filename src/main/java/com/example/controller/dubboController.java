package com.example.controller;


import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.example.Enum.GWBusinessEnum;
import com.example.dto.request.GWProductVo;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWRpcResponse;
import com.example.dubbo.GateWayDubbo;
import com.example.utils.MatchRequestUtils;
import com.example.utils.UUIDUtils;

import com.example.rpc.GenericRpcInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Future;

@RestController
@RequestMapping("duboo")
public class dubboController {

    @Autowired
    private GateWayDubbo gateWayDubbo;

    /**
     * 泛化模拟dubbo调用
     * @param
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test() {

        GWRpcRequest gwRpcRequest = new GWRpcRequest();

        GWProductVo reqVo = new GWProductVo();
        reqVo.setProductType_gw("02");
        reqVo.setUserBusChannel_gw("STAGE-APP");

        gwRpcRequest.setData_gw(reqVo);

        gwRpcRequest.setRequestId_gw(UUIDUtils.getUUID());
        gwRpcRequest.setSysId_gw(UUIDUtils.getUUID());

        gwRpcRequest.setBusinesscode_gw(GWBusinessEnum.queryProduct.getCode());
        GWRpcResponse gwRpcResponse = gateWayDubbo.dubboInvoke(gwRpcRequest);
        return gwRpcResponse.toString();
    }







}

