package com.example.controller;


import com.alibaba.dubbo.rpc.service.GenericService;
import com.example.Enum.GWBusinessEnum;
import com.example.dto.request.GWProductVo;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWRpcResponse;
import com.example.utils.MatchRequestUtils;
import com.example.utils.UUIDUtils;

import com.example.rpc.GenericRpcInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("duboo")
public class dubboController {

    /**
     * 泛华模拟dubbo调用
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
        GWRpcResponse gwRpcResponse = doDubboInvoke(gwRpcRequest);
        return gwRpcResponse.toString();
    }


    /**
     * 网关最外部dubbo接口
     * @param rpcRequest
     */
    private GWRpcResponse doDubboInvoke(GWRpcRequest rpcRequest){

        try{

            String businesscode = rpcRequest.getBusinesscode_gw();
            GWBusinessEnum gwBusinessEnum = GWBusinessEnum.getInstance(businesscode);

            String interfaceName = gwBusinessEnum.getInterfaceName();
            String methodName = gwBusinessEnum.getMethodName();
            String serviceRequestName = gwBusinessEnum.getServiceRequestName();


            //将对象转换为map-需根据映射为服务端参数
            Map map = MatchRequestUtils.toBeanServiceMap(rpcRequest,gwBusinessEnum);

            GenericService genericService = GenericRpcInstance.getInstance(interfaceName);

            Map result =  (Map)genericService.$invoke(methodName,
                    new String[]{serviceRequestName}, new Object[]{map});

            System.out.println(result);



            GWRpcResponse gwRpcResponse = MatchRequestUtils.toMapServiceBean(result,gwBusinessEnum);

            return gwRpcResponse;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }






}

