package com.mc.kafkascript.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "outclient")
@Data
@Component
public class FeignProperties {
    /*maxConnTotaL: 500
    laxconnPerRoute: 200
    maxIdleTime: 60
    connectTimeoutMillis: 2000
    readTimeoutMillis: 3000
    retry: false
    proxyIp: 10.181.8.38
    proxyPort: 10040
    isProxy: false
    url: http://10.150.35.156:80*/
    /*最大连接数*/
    private Integer maxConnTotal = 500;
   /*最大路由并发数*/
    private Integer maxConnPerRoute = 200;

    /*最大空闲时间*/
    private Long maxIdleTime = 60l;
    /*连接时间*/
    private Integer connectTimeoutMillis = 2000;
    /*读取时间*/
    private Integer readTimeoutMillis = 3000;

    private Boolean retry = false;

    private String proxyIp;//代理ip

    private int proxyPort;//代理端口

    private String isProxy = "false";//代理开关


}
