package com.example.dubbo;


import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.example.Enum.GWBusinessEnum;
import com.example.dto.BaseGWRpcBean;
import com.example.dto.request.GWProductVo;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWProductRespVo;
import com.example.dto.response.GWRpcResponse;
import com.example.rpc.GenericRpcInstance;
import com.example.utils.MatchRequestUtils;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class GateWayDubboImpl implements  GateWayDubbo{
    @Override
    public GWRpcResponse<BaseGWRpcBean> dubboInvoke(GWRpcRequest<BaseGWRpcBean> rpcRequest) {

        try{

            String businesscode = rpcRequest.getBusinesscode_gw();
            GWBusinessEnum gwBusinessEnum = GWBusinessEnum.getInstance(businesscode);

            String interfaceName = gwBusinessEnum.getInterfaceName();
            String methodName = gwBusinessEnum.getMethodName();
            String serviceRequestName = gwBusinessEnum.getServiceRequestName();


            //将对象转换为map-需根据映射为服务端参数
            Map map = MatchRequestUtils.toBeanServiceMap(rpcRequest,gwBusinessEnum);

            GenericService genericService = GenericRpcInstance.getInstance(interfaceName);

            genericService.$invoke(methodName,
                    new String[]{serviceRequestName}, new Object[]{map});

            Future<Object> resultFuture = RpcContext.getContext().getFuture();

          /*  Map result =  (Map)genericService.$invoke(methodName,
                    new String[]{serviceRequestName}, new Object[]{map});*/

            Map result = (Map)resultFuture.get(5, TimeUnit.SECONDS);



            GWRpcResponse gwRpcResponse = MatchRequestUtils.toMapServiceBean(result,gwBusinessEnum);

            return gwRpcResponse;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
