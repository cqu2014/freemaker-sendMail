package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.PO.SystemEmailPo;
import com.example.demo.mapper.SystemEmailPoMapper;
import com.example.demo.service.IMailTemplateService;
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
