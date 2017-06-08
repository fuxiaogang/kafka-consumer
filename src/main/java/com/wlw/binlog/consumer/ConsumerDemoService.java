package com.wlw.binlog.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author fuxg
 * @create 2016-12-15 11:53
 */
@Service
public class ConsumerDemoService implements IConsumer {

    private static Logger log = LoggerFactory.getLogger(ConsumerDemoService.class);

    @Override
    public String topic() {
        return "synctable_user";
    }

    @Override
    public void process(String userMessage) {
        JSONArray array = JSON.parseArray(userMessage);
        for (Object object : array) {
            domessage((JSONObject) object);
        }
    }

    private void domessage(JSONObject object) {
        log.info("read message from kafak:\n{}", object);
    }


}
