package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.model.EmailTemplate;
import com.example.demo.model.MailParameterRequest;
import com.example.demo.service.IMailTemplateService;
import com.example.demo.service.MailService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13.
 */

@Service
@Slf4j
public class MailserviceImpl implements MailService {
    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;

    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    //从数据库查找邮件模板
    @Autowired
    IMailTemplateService iMailTemplateService;


    public void sendMessageWithStringTemplate(MailParameterRequest mailParameterRequest) {
        try {
            //mimeMessage类型
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //设置富文本类型
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            //发送给谁
            helper.setTo(InternetAddress.parse("oliver.wang@shein.com"));
            //邮件标题
            helper.setSubject("[" + mailParameterRequest.getTitle() + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "]");

            Map<String, Object> model = new HashMap<>();
            model.put("params", mailParameterRequest.getMessage());
            try {
                Template template = configurer.getConfiguration().getTemplate(mailParameterRequest.getTemplate());
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

                    helper.setText(text, true);
                    mailSender.send(mimeMessage);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     *  字符串模板测试发送邮件
     */
    private void sendFromString() {
        StringWriter writer = new StringWriter();
        String content = "你的名字是： ${name !\"\"}";

        Configuration configuration = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("contract", content);

        configuration.setTemplateLoader(stringLoader);

        Template template = null;

        Map<String,Object> root = new HashMap<>();
        root.put("name", "王振");
        try {
            template = configuration.getTemplate("contract","utf-8");
            template.process(root, writer);
            System.out.println(writer.toString());
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void sendMessageMail(MailParameterRequest mailParameterRequest){
        //从数据库里获取字符串类型模板
        String emailTemplateListString = iMailTemplateService.queryById(35).getEmailTemplateList();

        List<EmailTemplate> emailTemplateList = JSONArray.parseArray(emailTemplateListString,EmailTemplate.class);

        String templateString = emailTemplateList.get(0).getContent().getEmailTemplateContent();

        StringWriter writer = new StringWriter();
        Map<String, Object> map=new HashMap<>();
        map.put("params", mailParameterRequest.getMessage());
        Template template;
        try {
            //利用StringReader生成模板
            template = new Template("template-name", new StringReader(templateString), new Configuration(new Version("2.3.23")));
            //替换dataModel并写入StringWriter
            template.process(map, writer);

            //mimeMessage类型
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //设置富文本类型
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            helper.setTo(InternetAddress.parse("286931986@qq.com"));//发送给谁
            helper.setSubject("[Oliver has a test]");//邮件标题
            helper.setText(writer.toString(), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            log.error("sendMessageMail:exception",e);
        }
    }
}
