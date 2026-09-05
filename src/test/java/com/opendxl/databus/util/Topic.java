/*---------------------------------------------------------------------------*
 * Copyright (c) 2024 Musarubra, LLC - All Rights Reserved.                     *
 *---------------------------------------------------------------------------*/

package com.opendxl.databus.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Topic {
    public static String getRandomTopicName() {
        return UUID.randomUUID().toString();
    }

    public static class Builder {
        private String topicName = getRandomTopicName();
        private int partitions = 1;
        private int replicationFactor = 1;
        private String zkHost = Constants.ZOOKEEPER_HOST;
        private String zkPort = Constants.ZOOKEEPER_PORT;

        public Builder partitions(final int partitions) {
            this.partitions = partitions;
            return this;
        }

        public Builder zkHost(final String zkHost) {
            this.zkHost = zkHost;
            return this;
        }

        public Builder zkPort(final String zkPort) {
            this.zkPort = zkPort;
            return this;
        }

        public Builder topicName(final String topicName) {
            this.topicName = topicName;
            return this;
        }

        public Builder replicationFactor(final int replicationFactor) {
            this.replicationFactor = replicationFactor;
            return this;
        }

        public String go() {
            // Topics are created through the AdminClient since Kafka 3.x removed the ZooKeeper based TopicCommand
            final Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_HOST + ":" + Constants.KAFKA_PORT);
            final NewTopic topic = new NewTopic(topicName, partitions, (short) replicationFactor);
            try (AdminClient adminClient = AdminClient.create(props)) {
                adminClient.createTopics(Collections.singleton(topic)).all().get(30000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                throw new RuntimeException("Error creating a new Kafka topic " + topicName, e);
            }
            return topicName;
        }
    }
}
