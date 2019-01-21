package com.example.demo.service;

import com.example.demo.model.MailParameterRequest;

/**
 * Created by Administrator on 2017/12/13.
 */
public interface MailService {
    /**
     * 发送邮件
     *
     * @param mailParameterRequest
     */
    void sendMessageMail(MailParameterRequest mailParameterRequest);

    /**
     * 使用字符串模板发送邮件
     *
     * @param mailParameterRequest
     */
    void sendMessageWithStringTemplate(MailParameterRequest mailParameterRequest);

}