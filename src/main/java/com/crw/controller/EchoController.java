package com.crw.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EchoController {

    @GetMapping("/echo/hello")
    public String echo(String msg) {
        log.debug("DEBUG ----> echo:{}", msg);
        log.info("INFO  ----> echo:{}", msg);
        log.warn("WARN  ----> echo:{}", msg);
        log.error("ERROR  ----> echo:{}", msg);
        return "hello, " + msg;
    }
}
