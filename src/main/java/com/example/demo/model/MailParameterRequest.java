package com.example.demo.model;

import lombok.Data;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2019/1/17 15:04
 */
@Data
public class MailParameterRequest {

    private String title;

    private String template;

    private Message message;
}
