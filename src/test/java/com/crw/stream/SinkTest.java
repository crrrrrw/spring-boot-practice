package com.crw.stream;

import com.crw.App;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class SinkTest {

    @Autowired
    private SinkSender sinkSender;

    @Test
    public void contextLoads() {
        sinkSender.output()
                .send(MessageBuilder.withPayload("hello, i'm output ").build());
    }

    @Test
    public void contextLoadsJson() {
        sinkSender.output()
                .send(MessageBuilder.withPayload("{\"id\":1,\"nickName\":\"张三\",\"password\":\"111111\",\"mobile\":\"11100001001\",\"createAt\":1531322361803,\"updateAt\":1531322361803}}").build());
    }
}
