package com.example.controller;


import com.example.Enum.GWBusinessEnum;
import com.example.utils.UUIDUtils;

import com.suixingpay.fin.athena.dubbo.GateWayDubbo;
import com.suixingpay.fin.athena.dubbo.base.GWRpcRequest;
import com.suixingpay.fin.athena.dubbo.base.GWRpcResponse;
import com.suixingpay.fin.athena.dubbo.dto.request.GWProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("duboo")
public class dubboController {

    private static Logger logger = LoggerFactory
            .getLogger(dubboController.class);

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
        long startTime=System.currentTimeMillis(); //获取结束时间
        GWRpcResponse gwRpcResponse = gateWayDubbo.dubboInvoke(gwRpcRequest);
        long endTime=System.currentTimeMillis(); //获取结束时间
        logger.info("service run time： "+(endTime-startTime));
        return gwRpcResponse.toString();
    }







}

