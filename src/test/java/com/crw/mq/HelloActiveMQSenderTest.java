package com.crw.mq;

import com.crw.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class HelloActiveMQSenderTest {

    @Autowired
    private HelloActiveMQSender sender;

    @Test
    public void sendHello() {
        sender.send("Hello " + new Date());
    }
}
