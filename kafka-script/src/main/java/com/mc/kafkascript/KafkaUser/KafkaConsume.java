/*
package com.mc.kafkascript.KafkaUser;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

*/
/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.KafkaUser
 * 类名称:     KafkaConsume
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/23 10:02
 *//*

@Component
@Slf4j
public class KafkaConsume {
    @org.springframework.kafka.annotation.KafkaListener(topics = {"acct-dealException"}, containerFactory =
            "kafkaListenerContainerFactory")
    public void consume(List<ConsumerRecord<String, String>> list, Acknowledgment ack) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir")
                + File.separator + "dealException.txt"), true);
        String msg;
        try {
            for (ConsumerRecord consumerRecord : list) {

                msg = consumerRecord.value().toString().concat("\n");
                log.error("usergroup_mc msg is:" + msg + ",offset is" + consumerRecord.offset() + ",receive time is:"
                        + new Date(consumerRecord.timestamp()));

                fileOutputStream.write(msg.getBytes());
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            */
/*避免重复消费调用*//*

            ack.acknowledge();
        }
    }
}
*/
