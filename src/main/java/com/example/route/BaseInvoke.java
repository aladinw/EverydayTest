package com.example.route;


import com.example.Enum.GWBusinessEnum;
import org.apache.http.HttpResponse;

import java.util.Map;

public interface BaseInvoke {

    Map dubboInvoke(Map map, GWBusinessEnum gwBusinessEnum);

    HttpResponse httpInvoke();
}
