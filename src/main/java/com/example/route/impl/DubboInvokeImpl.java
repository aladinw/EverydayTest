package com.example.route.impl;


import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.example.Enum.GWBusinessEnum;
import com.example.route.BaseInvoke;
import com.example.rpc.GenericRpcInstance;
import org.apache.http.HttpResponse;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DubboInvokeImpl  implements BaseInvoke<Map,GWBusinessEnum,Map>{
    @Override
    public Map doInvoke(Map map, GWBusinessEnum gwBusinessEnum) {

        try{

            String interfaceName = gwBusinessEnum.getInterfaceName();
            String methodName = gwBusinessEnum.getMethodName();
            String serviceRequestName = gwBusinessEnum.getServiceRequestName();

            GenericService genericService = GenericRpcInstance.getInstance(interfaceName);

            //开启异步,因此此处没有任何返回值
            genericService.$invoke(methodName, new String[]{serviceRequestName}, new Object[]{map});

            Future<Object> resultFuture = RpcContext.getContext().getFuture();

            Map result = (Map)resultFuture.get(5, TimeUnit.SECONDS);

            return result;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }

}
