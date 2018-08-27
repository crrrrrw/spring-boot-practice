package com.crw.service;


import com.crw.App;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleTextMail() {
        emailService.sendSimpleTextMail("120755274@qq.com", "测试主题", "这是一个简单的邮件。");
    }

    @Test
    public void sendHtmlMail() {
        String html = "<html><body><h1>你好，这是一封带点html格式的邮件！</h1></body></html>";
        emailService.sendHtmlMail("120755274@qq.com", "html测试主题", html);
    }

    @Test
    public void sendAttendedFileMail() {
        String html = "<html><body><h1>你好，这是一封带点html格式而且又有附件的邮件！</h1></body></html>";
        String filePath = "/Users/chenruiwen/Documents/workspace/myRepositories/spring-boot-practice/src/main/resources/banner.txt";
        emailService.sendAttendedFileMail("120755274@qq.com", "html带附件测试主题", html, filePath);
    }

    /**
     * 测试html嵌入静态资源
     */
    @Test
    public void sendInlineMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("120755274@qq.com");
        helper.setTo("120755274@qq.com");
        helper.setSubject("html嵌入静态资源测试主题");
        helper.setText("<html><body><h1>你好，这是一封带点html格式而且又有静态资源图片的邮件！</h1><img src=\"cid:source\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("/Users/chenruiwen/Desktop/timg.jpeg"));
        helper.addInline("source", file);

        javaMailSender.send(mimeMessage);
    }


    @Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("120755274@qq.com");
        helper.setTo("120755274@qq.com");
        helper.setSubject("模板测试主题");

        Context context = new Context();
        context.setVariable("name", "chenruiwen");
        String text = templateEngine.process("emailTemplate", context);
        helper.setText(text, true);

        javaMailSender.send(mimeMessage);
    }

}
