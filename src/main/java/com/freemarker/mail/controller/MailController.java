package com.freemarker.mail.controller;

import com.alibaba.fastjson.JSON;
import com.freemarker.mail.model.MailParameterRequest;
import com.freemarker.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/13.
 */

@RestController
@Slf4j
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 接受参数发送邮件
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Object sendMailMessage(@RequestBody MailParameterRequest mailParameterRequest) {
        log.info("sendMailMessage:input data [{}]",JSON.toJSON(mailParameterRequest));

        mailService.sendMessageMail(mailParameterRequest);

        return "SUCESS";
    }

    @GetMapping(value = "parameter")
    public Object parameter(@RequestParam String wang){
        return wang;
    }
}
