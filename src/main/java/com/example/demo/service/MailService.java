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

    //void SendFromString(MailParameterRequest mailParameterRequest) throws IOException;
}
