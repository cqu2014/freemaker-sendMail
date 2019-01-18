package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2019/1/18 10:41
 */
@Data
public class EmailTemplate {
    /*语言*/
    private String language;

    /*站点*/
    @JSONField(name = "site_uid")
    private List<String> siteUid;

    /*发件人邮箱*/
    @JSONField(name = "from_mail")
    private String fromMail;

    /*模板体*/
    private TemplateContentInfo content;
}
