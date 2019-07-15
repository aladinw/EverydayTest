package com.example.filter;


import com.alibaba.dubbo.rpc.*;
import com.example.dto.request.GWRpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecondFilter implements Filter {

    private static Logger log = LoggerFactory
            .getLogger(SecondFilter.class);


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        GWRpcRequest m = (GWRpcRequest)invocation.getArguments()[0];


        Result result = invoker.invoke(invocation);
        log.info("-------------走到SecondFilter-------------------");
        Object obj = result.getValue();
        return result;

    }
}
