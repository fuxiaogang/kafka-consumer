/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.wlw.binlog.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.wlw.binlog.KafkaConsumerApplication.applicationContext;

/**
 * 类KafkaConsumerService.java的实现描述：消费接收类
 */
public class KafkaConsumerServiceImpl {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    final static Map<String, IConsumer> reg_consumers = new HashMap<>();

    private Map<String, IConsumer> getConsumers() {
        if (reg_consumers.isEmpty()) {
            synchronized (reg_consumers) {
                if (reg_consumers.isEmpty()) {
                    reg_consumers.putAll(applicationContext.getBeansOfType(IConsumer.class)); //来自spring bean的消费者
                    for (String key : reg_consumers.keySet()) {
                        logger.info("regist consumer:" + key);
                    }
                }
            }
        }
        return reg_consumers;
    }

    public void processMessage(Map<String, Map<Integer, String>> msgs) {
        for (Map.Entry<String, Map<Integer, String>> entry : msgs.entrySet()) {
            String topic = entry.getKey();
            LinkedHashMap<Integer, String> messages = (LinkedHashMap<Integer, String>) entry.getValue();
            Collection<String> values = messages.values();
            for (Iterator<String> iterator = values.iterator(); iterator.hasNext(); ) {
                String message = "[" + iterator.next() + "]";
                Map<String, IConsumer> consumers = getConsumers();
                for (String name : consumers.keySet()) {
                    IConsumer c = consumers.get(name);
                    if (c.topic().equals(topic)) {
                        c.process(message);
                    }
                }
            }
        }
    }


}
