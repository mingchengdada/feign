package com.mc.kafkascript.KafkaUser;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * function description.
 * <p>
 * <p><h2>Descriptions</h2>
 * <h3>Project</h3> PinkStone_production
 * <h3>Package</h3> com.migu.pinkstone.config
 * </p>
 * <p><h2>Change History</h2>
 * 2018/4/9 17:08 | mael | created
 * </p>
 *
 * @author mael
 * @version 1.0.0
 */
public class UserInfoPartitioner implements Partitioner {
    private final AtomicInteger counter = new AtomicInteger(new SecureRandom().nextInt());
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        // topic分区的数量
        int numPartitions = partitions.size();
        // topicl可用分区的数量
        List<PartitionInfo> availablePartitions = cluster.availablePartitionsForTopic(topic);
        if (keyBytes == null) {
            if(null !=key) {
                if (availablePartitions.size() > 0) {
                    int part = key.hashCode() % availablePartitions.size();
                    if (part < 0) {
                        part = 0;
                    }
                    return availablePartitions.get(part).partition();
                } else {
                    // no partitions are available, give a non-available partition
                    return key.hashCode() % numPartitions;
                }
            } else {
                    int nextValue = this.counter.getAndIncrement();
                    if (availablePartitions.size() > 0) {
                        int part = Utils.toPositive(nextValue) % availablePartitions.size();
                        return ((PartitionInfo)availablePartitions.get(part)).partition();
                    } else {
                        return Utils.toPositive(nextValue) % numPartitions;
                    }
            }
        } else {
            // hash the keyBytes to choose a partition
            return toPositive(key.hashCode()) % numPartitions;
        }
    }

    private static int toPositive(int number) {
        return number & 2147483647;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
