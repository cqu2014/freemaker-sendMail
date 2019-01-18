package com.example.demo.mapper;

import com.example.demo.entity.PO.SystemEmailPo;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemEmailPoMapper {

    SystemEmailPo selectByPrimaryKey(Integer id);
}