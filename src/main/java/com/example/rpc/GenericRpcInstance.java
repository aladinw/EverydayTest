package com.example.rpc;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;

public class GenericRpcInstance {


    public static  GenericService getInstance(String interfaceName){

        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://172.16.154.48:2181?backup=172.16.154.49:2181,172.16.154.50:2181");

        application.setRegistry(registry);


        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();

        reference.setInterface(interfaceName);
        // 声明为泛化接口
        reference.setGeneric(true);
        reference.setApplication(application);
        reference.setAsync(true);
        reference.setTimeout(5000);

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

        return genericService;

    }

}
