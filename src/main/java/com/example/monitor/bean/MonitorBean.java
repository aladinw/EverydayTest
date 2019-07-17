package com.example.monitor.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MonitorBean {

    private String businesscode;
    private String responsecode;
    private String responsemessage;
    private long time;


}
