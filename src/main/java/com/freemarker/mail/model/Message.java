package com.freemarker.mail.model;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/13.
 */


@Data
public class Message {

    private String messageCode;

    private String messageStatus;

    private String cause;
}
