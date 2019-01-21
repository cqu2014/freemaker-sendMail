package com.freemarker.mail.mapper;

import com.freemarker.mail.entity.PO.SystemEmailPo;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemEmailPoMapper {

    SystemEmailPo selectByPrimaryKey(Integer id);
}