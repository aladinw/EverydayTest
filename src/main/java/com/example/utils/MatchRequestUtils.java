package com.example.utils;


import com.example.Enum.GWBusinessEnum;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWRpcResponse;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * 映射工具类
 */
public class MatchRequestUtils {

    /**
     *  将网关Request映射为服务端的Request
     */
    public static Map toBeanServiceMap (GWRpcRequest rpcRequest, GWBusinessEnum gwBusinessEnum){


        JSONObject jsonObject = JSONObject.fromObject(gwBusinessEnum.getRequestMatch());

        Map map = BeanToMapUtils.toObjectMap(rpcRequest,jsonObject);

        return map;
    }


    /**
     * 将泛化服务端返回的map转换为对象
     *
     *FloanRpcRequest floanRpcRequest =  BeanToMapUtils.toBean(FloanRpcRequest.class,result);
     * @param map
     * @return
     */
    public static GWRpcResponse toMapServiceBean(Map map, GWBusinessEnum gwBusinessEnum) throws  Exception{

        JSONObject jsonObject = JSONObject.fromObject(gwBusinessEnum.getResponseMatch());

        GWRpcResponse gwRpcResponse = BeanToMapUtils.toBean(GWRpcResponse.class,map,jsonObject);

        return gwRpcResponse;
    }

}
