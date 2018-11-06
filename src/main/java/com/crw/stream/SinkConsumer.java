package com.crw.stream;

import com.crw.model.SbpUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding({Sink.class, SinkSender.class})
@Slf4j
public class SinkConsumer {

    //@StreamListener(Sink.INPUT)
    public void receive(Object msg) {
        log.info("receive:{}", msg);
    }

    @StreamListener(Sink.INPUT)
    public void receive(SbpUser msg) {
        log.info("receive:{}", msg.toString());
    }

}
