package com.wlw.binlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fuxg
 * @create 2016-11-30 12:35
 */
@SpringBootApplication
public class KafkaConsumerApplication {

    private static Logger log = LoggerFactory.getLogger(KafkaConsumerApplication.class);

    public static ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(KafkaConsumerApplication.class);
        app.setWebEnvironment(false);
        Set<Object> set = new HashSet<Object>();
        set.add("classpath:applicationContext.xml");
        app.setSources(set);
        applicationContext = app.run(args);
    }
}
