package com.example.dto.request;


import com.example.dto.BaseGWRpcBean;

public class GWProductVo extends BaseGWRpcBean {

    private String userBusChannel_gw;
    private String productType_gw;
    private String productId_gw;
    private String feeType_gw;

    public GWProductVo() {
    }

    public String getUserBusChannel_gw() {
        return userBusChannel_gw;
    }

    public void setUserBusChannel_gw(String userBusChannel_gw) {
        this.userBusChannel_gw = userBusChannel_gw;
    }

    public String getProductType_gw() {
        return productType_gw;
    }

    public void setProductType_gw(String productType_gw) {
        this.productType_gw = productType_gw;
    }

    public String getProductId_gw() {
        return productId_gw;
    }

    public void setProductId_gw(String productId_gw) {
        this.productId_gw = productId_gw;
    }

    public String getFeeType_gw() {
        return feeType_gw;
    }

    public void setFeeType_gw(String feeType_gw) {
        this.feeType_gw = feeType_gw;
    }
}
