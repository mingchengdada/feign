package com.mc.kafkascript.feign;

import feign.Client;
import feign.Request;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.concurrent.TimeUnit;

@EnableConfigurationProperties(FeignProperties.class)
public class FeignHttpClientProxyConf {
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
        builder.evictIdleConnections(feignProperties.getMaxIdleTime().longValue(), TimeUnit.SECONDS);

        ApacheHttpClient delegate = new ApacheHttpClient(builder.build());
        return new LoadBalancerFeignClient(delegate, cachingFactory, clientFactory);
    }

    @Bean
    public static Request.Options requestOptions(ConfigurableEnvironment env, FeignProperties feignProperties) {
        return new Request.Options(feignProperties.getConnectTimeoutMillis(), feignProperties
                .getReadTimeoutMillis());
    }
}