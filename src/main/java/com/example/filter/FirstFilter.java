package com.example.filter;


import com.alibaba.dubbo.rpc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FirstFilter implements Filter {

    private static Logger log = LoggerFactory
            .getLogger(FirstFilter.class);


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        Result result = invoker.invoke(invocation);
        log.info("-------------走到FirstFilter-------------------");
        return result;

    }
}
