package com.freemarker.mail.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2019/1/18 10:38
 */
@Data
public class TemplateContentInfo {
    /*主题*/
    @JSONField(name = "email_title")
    private String emailTitle;

    /*类型*/
    @JSONField(name = "email_type")
    private String emailType;

    /*复制邮件模板*/
    @JSONField(name = "duplication_email_template")
    private String duplicationEmailTemplate;

    /*邮件模板内容*/
    @JSONField(name = "email_template_content")
    private String emailTemplateContent;
}
