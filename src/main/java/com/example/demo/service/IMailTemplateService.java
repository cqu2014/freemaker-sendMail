package com.example.demo.service;

import com.example.demo.entity.PO.SystemEmailPo;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2019/1/18 10:25
 */
public interface IMailTemplateService {
    /**
     * 根据Id获取邮件模板
     *
     * @param id
     * @return
     */
    SystemEmailPo queryById(Integer id);
}
