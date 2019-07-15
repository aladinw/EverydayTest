package com.example.Enum;


import lombok.Data;

/**
 * 网关业务类型
 * 后期改成配置
 *
 左边是来源,右边是目标，如果不配置目标,则直接拿过来

 调用映射json;对于调用而言,需转换为服务端字段。左边是来源,右边是目标。将来源字段转换为目标字段
 {
 "requestId_gw": "requestId",
 "sysId_gw": "sysId",
 "serviceName_gw": "serviceName",
 "businesscode_gw": "businesscode",
     "data": {
     "key":"data",
         "value":{
             "userBusChannel_gw": "userBusChannel",
             "productType_gw": "productType",
             "productId_gw": "productId",
             "feeType_gw": "feeType"
         }

     }
 }


 响应映射json;对于相应而言,需将需要的字段从目标中获取。左边是目标,右边是来源。将
 {
     "requestId_gw": "requestId",
     "sysId_gw": "sysId",
     "bSuccess_gw": "bSuccess",
     "code_gw": "code",
     "message_gw": "message",
     "data_gw": {
                 "key":"data",
                 "value":{
                 "productFeeList_gw": "productFeeList",
                 "productInfoList_gw": "productInfoList"
                         }

             }
 }



 */
public enum GWBusinessEnum {

    queryProduct("100001"
            , "查询产品接口"
            ,"com.suixingpay.fin.floan.dubbo.rs.FloanDubbo"
            ,"queryProduct"
            ,"com.suixingpay.fin.floan.dubbo.rs.dto.FloanRpcRequest",
            "{\n" +
                    "   \"requestId_gw\": \"requestId\",\n" +
                    "   \"sysId_gw\": \"sysId\",\n" +
                    "   \"serviceName_gw\": \"serviceName\",\n" +
                    "   \"businesscode_gw\": \"businesscode\",\n" +
                    "   \"data_gw\": {\n" +
                    "      \"classname\":\"com.suixingpay.fin.floan.dubbo.rs.vo.reqVo.ProductReqVo\",\n" +
                    "      \"key\":\"data\",\n" +
                    "      \"value\":{\n" +
                    "          \"userBusChannel_gw\": \"userBusChannel\",\n" +
                    "         \"productType_gw\": \"productType\",\n" +
                    "         \"productId_gw\": \"productId\",\n" +
                    "         \"feeType_gw\": \"feeType\"\n" +
                    "      }\n" +
                    "     \n" +
                    "   }\n" +
                    "}"
                    ,"{\n" +
            "   \"requestId_gw\": \"requestId\",\n" +
            "   \"sysId_gw\": \"sysId\",\n" +
            "   \"bSuccess_gw\": \"bSuccess\",\n" +
            "   \"code_gw\": \"code\",\n" +
            "   \"message_gw\": \"message\",\n" +
            "   \"data_gw\": {\n" +
            "     \"classname\":\"com.example.dto.response.GWProductRespVo\",\n" +
            "      \"key\":\"data\",\n" +
            "      \"value\":{\n" +
            "          \"productFeeList_gw\": {\n" +
            "            \"key\":\"productFeeList\",\n" +
            "            \"classname\":\"com.example.dto.response.ProductFeeVo\"\n" +
            "\n" +
            "          },\n" +
            "         \"productInfoList_gw\": {\n" +
            "            \"key\":\"productInfoList\",\n" +
            "            \"classname\":\"com.example.dto.response.ProductInfoVo\"\n" +
            "         }\n" +
            "      }\n" +
            "     \n" +
            "   }\n" +
            "}",0);



    private String code;
    private String desc;
    private String interfaceName;//服务端映射接口名称
    private String methodName;//服务端映射方法名称
    private String serviceRequestName;//服务端映射Request类名
    private String requestMatch;//调用映射关系
    private String responseMatch;//相应映射关系
    private int targetProtocol;//目标协议类型  0 dubbo   1 http


    GWBusinessEnum(String code
            , String desc
            ,String interfaceName
            ,String methodName
            ,String serviceRequestName
            ,String requestMatch
            ,String responseMatch
            ,int targetProtocol) {

        this.code = code;
        this.desc = desc;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.serviceRequestName = serviceRequestName;
        this.requestMatch = requestMatch;
        this.responseMatch = responseMatch;
        this.targetProtocol = targetProtocol;
    }

    public static GWBusinessEnum getInstance(String code) {

        GWBusinessEnum[] values = GWBusinessEnum.values();
        for (GWBusinessEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }

        return null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServiceRequestName() {
        return serviceRequestName;
    }

    public void setServiceRequestName(String serviceRequestName) {
        this.serviceRequestName = serviceRequestName;
    }


    public String getRequestMatch() {
        return requestMatch;
    }

    public void setRequestMatch(String requestMatch) {
        this.requestMatch = requestMatch;
    }


    public String getResponseMatch() {
        return responseMatch;
    }

    public void setResponseMatch(String responseMatch) {
        this.responseMatch = responseMatch;
    }

    public int getTargetProtocol() {
        return targetProtocol;
    }

    public void setTargetProtocol(int targetProtocol) {
        this.targetProtocol = targetProtocol;
    }
}
