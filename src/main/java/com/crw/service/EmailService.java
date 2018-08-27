package com.crw.service;

public interface EmailService {

    /**
     * 发送简单文本信息的email
     *
     * @param dstAddress 目标地址
     * @param subject    主题
     * @param content    内容
     */
    void sendSimpleTextMail(String dstAddress, String subject, String content);

    /**
     * 发送 html格式信息的email
     *
     * @param dstAddress  目标地址
     * @param subject     主题
     * @param htmlContent 内容
     */
    void sendHtmlMail(String dstAddress, String subject, String htmlContent);

    /**
     * 发送带附件文件的email
     *
     * @param dstAddress 目标地址
     * @param subject    主题
     * @param content    内容
     * @param filePath   文件路径
     */
    void sendAttendedFileMail(String dstAddress, String subject, String content, String filePath);
}
