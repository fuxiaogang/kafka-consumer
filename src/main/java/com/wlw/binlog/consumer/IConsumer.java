package com.wlw.binlog.consumer;

import kafka.consumer.ConsumerIterator;

import java.util.Collection;
import java.util.List;

/**
 * @author fuxg
 * @create 2016-11-30 11:07
 */
public interface IConsumer {
    public String topic();

    public void process(String userMessage);
}
