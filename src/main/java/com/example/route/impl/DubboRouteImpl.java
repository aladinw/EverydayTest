package com.example.route.impl;


import com.example.Enum.GWBusinessEnum;
import com.example.dto.BaseGWRpcBean;
import com.example.dto.request.GWRpcRequest;
import com.example.dto.response.GWRpcResponse;
import com.example.route.BaseInvoke;
import com.example.route.BaseRoute;
import com.example.utils.MatchRequestUtils;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * dubbo的路由逻辑
 */
public class DubboRouteImpl implements BaseRoute {

    @Override
    public GWRpcResponse<BaseGWRpcBean> doRoute(GWRpcRequest rpcRequest) {

        try{

            BaseInvoke baseInvoke = new DubboInvokeImpl();

            String businesscode = rpcRequest.getBusinesscode_gw();
            GWBusinessEnum gwBusinessEnum = GWBusinessEnum.getInstance(businesscode);

            int targetProtocol = gwBusinessEnum.getTargetProtocol();

            switch (targetProtocol){
                case 0://目标为dubbo协议

                    //将对象转换为map-需根据映射为服务端参数
                    Map map = MatchRequestUtils.toBeanServiceMap(rpcRequest,gwBusinessEnum);

                    Map result = baseInvoke.dubboInvoke(map,gwBusinessEnum);

                    GWRpcResponse gwRpcResponse = MatchRequestUtils.toMapServiceBean(result,gwBusinessEnum);
                    return gwRpcResponse;

                case 1://目标为http协议
                    //TODO
                    baseInvoke = new HttpInvokeImpl();
                    HttpResponse httpResponse = baseInvoke.httpInvoke();

                    return null;

                default://暂不支持
                    return null;
            }



        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Object doRoute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
