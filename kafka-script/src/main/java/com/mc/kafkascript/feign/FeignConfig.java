package com.mc.kafkascript.feign;

import feign.Client;
import feign.Retryer;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.redis.configuration
 * 类名称:     FeignConfig
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/20 11:12
 */
@EnableConfigurationProperties(FeignProperties.class)
public class FeignConfig {
    /**
     * @param feignProperties properties
     * @return  client LoadBalancerFeignClient
     */
    @Bean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory, SpringClientFactory clientFactory,
                              FeignProperties feignProperties) {

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setMaxConnTotal(feignProperties.getMaxConnTotal());
        builder.setMaxConnPerRoute(feignProperties.getMaxConnPerRoute());
        builder.setDefaultRequestConfig(
                RequestConfig.custom().setConnectTimeout(feignProperties.getConnectTimeoutMillis())
                        .setSocketTimeout(feignProperties.getReadTimeoutMillis()).build());
        if ("true".equals(feignProperties.getIsProxy())) {
            builder.setProxy(new HttpHost(feignProperties.getProxyIp(), feignProperties.getProxyPort()));
        }

        builder.evictIdleConnections(feignProperties.getMaxIdleTime().longValue(), TimeUnit.SECONDS);

        ApacheHttpClient delegate = new ApacheHttpClient(builder.build());
        return new LoadBalancerFeignClient(delegate, cachingFactory, clientFactory);
    }


}
