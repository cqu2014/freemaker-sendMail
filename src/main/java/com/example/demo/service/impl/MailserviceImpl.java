package com.example.demo.service.impl;

import com.example.demo.model.MailParameterRequest;
import com.example.demo.service.MailService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13.
 */

@Service
public class MailserviceImpl implements MailService {
    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;

    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;

    //发送邮件的模板引擎
    @Autowired
    private static FreeMarkerConfigurer configurer;


    public void sendMessageMail1(MailParameterRequest mailParameterRequest) {
        try {
            //mimeMessage类型
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //设置富文本类型
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            helper.setTo(InternetAddress.parse("oliver.wang@shein.com"));//发送给谁
            helper.setSubject("[" + mailParameterRequest.getTitle() + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "]");//邮件标题

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

    public static void sendFromString() {
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
        String templateString="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>消息通知</title>\n" +
                "</head>\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "    table {\n" +
                "        font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "        width: 100%;\n" +
                "        border-collapse: collapse;\n" +
                "    }\n" +
                "\n" +
                "    td, th {\n" +
                "        font-size: 1em;\n" +
                "        border: 1px solid #5B4A42;\n" +
                "        padding: 3px 7px 2px 7px;\n" +
                "    }\n" +
                "\n" +
                "    th {\n" +
                "        font-size: 1.1em;\n" +
                "        text-align: center;\n" +
                "        padding-top: 5px;\n" +
                "        padding-bottom: 4px;\n" +
                "        background-color: #24A9E1;\n" +
                "        color: #ffffff;\n" +
                "    }\n" +
                "</style>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <h2>邮件消息通知</h2>\n" +
                "    <table id=\"customers\">\n" +
                "        <tr>\n" +
                "            <th>MessageCode</th>\n" +
                "            <th>MessageStatus</th>\n" +
                "            <th>Cause</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>${(params.messageCode)!\"\"}</td>\n" +
                "            <td>${(params.messageStatus)!\"\"}</td>\n" +
                "            <td>${(params.cause)!\"\"}</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        StringWriter writer = new StringWriter();
        Map<String, Object> map=new HashMap<>();
        map.put("params", mailParameterRequest.getMessage());
        Template template = null;
        try {
            template = new Template("template-name", new StringReader(templateString), new Configuration(new Version("2.3.23")));
            template.process(map, writer);
            System.out.println(writer.toString());

            //mimeMessage类型
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //设置富文本类型
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            helper.setTo(InternetAddress.parse("oliver.wang@shein.com"));//发送给谁
            helper.setSubject("[" + mailParameterRequest.getTitle() + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "]");//邮件标题
            helper.setText(writer.toString(), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
