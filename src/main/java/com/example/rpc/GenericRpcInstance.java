package com.example.rpc;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;

public class GenericRpcInstance {

    private GenericService genericService = null;

    public static  GenericService getInstance(String interfaceName){

        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://172.16.154.48:2181?backup=172.16.154.49:2181,172.16.154.50:2181");

        application.setRegistry(registry);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();

        // 弱类型接口名 "com.suixingpay.fin.floan.dubbo.rs.FloanDubbo"
        reference.setInterface(interfaceName);
        // 声明为泛化接口
        reference.setGeneric(true);
        reference.setApplication(application);

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

        return genericService;

    }

}
