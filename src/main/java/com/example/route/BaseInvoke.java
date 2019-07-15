package com.example.route;




public interface BaseInvoke<T,V,R> {

    R doInvoke(T request, V parameter);

}
