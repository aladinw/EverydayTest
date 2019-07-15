package com.example.route;


public interface BaseRoute<T,V> {

    V doRoute(T request);


}
