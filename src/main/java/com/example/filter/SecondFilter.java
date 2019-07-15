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

        long startTime=System.currentTimeMillis();   //获取开始时间

        Result result = invoker.invoke(invocation);
        long endTime=System.currentTimeMillis(); //获取结束时间

        log.info("-------------走到SecondFilter-------------------");
        Object obj = result.getValue();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        return result;

    }
}
