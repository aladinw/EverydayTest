package com.example.route;

import com.example.dto.request.GWRpcRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BaseRoute<T> {

    T doRoute(GWRpcRequest<?> gwRpcRequest);

    T doRoute(HttpServletRequest request, HttpServletResponse response);

}
