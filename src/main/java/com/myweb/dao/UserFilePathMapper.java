package com.myweb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myweb.pojo.UserFilePath;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFilePathMapper extends BaseMapper<UserFilePath> {

    //基本CRUD已透過Mybatis Plus 配置完成

}
