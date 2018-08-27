package com.crw.service.impl;

import com.crw.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderAddress;

    @Override
    public void sendSimpleTextMail(String dstAddress, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderAddress);
        message.setTo(dstAddress);
        message.setSubject(subject);
        message.setText(content);

        try {
            javaMailSender.send(message);
            log.info("sendSimpleTextMail send success!");
        } catch (Exception e) {
            log.error("sendSimpleTextMail send error : {}", e);
        }

    }

    @Override
    public void sendHtmlMail(String dstAddress, String subject, String htmlContent) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(senderAddress);
            helper.setTo(dstAddress);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            log.info("sendHtmlMail send success!");
        } catch (Exception e) {
            log.error("sendHtmlMail send error : {}", e);
        }
    }

    @Override
    public void sendAttendedFileMail(String dstAddress, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderAddress);
            helper.setTo(dstAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));

            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            javaMailSender.send(message);
            log.info("sendAttendedFileMail send success!");
        } catch (MessagingException e) {
            log.error("sendAttendedFileMail send error : {}", e);
        }
    }
}
