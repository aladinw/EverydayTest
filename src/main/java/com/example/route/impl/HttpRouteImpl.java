package com.example.route.impl;


import com.example.dto.request.GWRpcRequest;
import com.example.route.BaseRoute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRouteImpl implements BaseRoute {
    @Override
    public Object doRoute(GWRpcRequest gwRpcRequest) {
        return null;
    }

    @Override
    public Object doRoute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
