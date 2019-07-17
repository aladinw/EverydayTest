package com.example.monitor;

import com.example.monitor.bean.MonitorBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GWMonitorService {


    private RedisTemplate redisTemplate;


    //接收调用数据
    public void receiveMonitor(MonitorBean monitorBean) {

        //发送mq消息



    }
}
