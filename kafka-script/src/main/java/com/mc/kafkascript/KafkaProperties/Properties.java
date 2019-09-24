package com.mc.kafkascript.KafkaProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.KafkaProperties
 * 类名称:     Properties
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/5 15:17
 */

@Data
@Component
@ConfigurationProperties(prefix = "kafka.consumer")
public class Properties {
    private String servers;

    private int retries;

    private int batchSize;

    private int bufferMemory;

    private String groupId;

    private int autoCommitInterval;

    private int sessionTimeout;

    private String autoOffsetReset;

    private int concurrency;

    private int pollTimeout;
}
