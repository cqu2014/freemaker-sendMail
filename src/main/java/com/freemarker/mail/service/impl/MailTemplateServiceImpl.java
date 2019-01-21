package com.freemarker.mail.service.impl;

import com.alibaba.fastjson.JSON;
import com.freemarker.mail.entity.PO.SystemEmailPo;
import com.freemarker.mail.mapper.SystemEmailPoMapper;
import com.freemarker.mail.service.IMailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2019/1/18 10:26
 */
@Service
@Slf4j
public class MailTemplateServiceImpl implements IMailTemplateService {

    @Autowired
    SystemEmailPoMapper systemEmailPoMapper;

    @Override
    public SystemEmailPo queryById(Integer id) {
        log.info("queryById:input data [{}]",String.valueOf(id));

        SystemEmailPo systemEmailPo = systemEmailPoMapper.selectByPrimaryKey(id);

        log.info("queryById:output data [{}]", JSON.toJSON(systemEmailPo));

        return systemEmailPo;
    }
}
