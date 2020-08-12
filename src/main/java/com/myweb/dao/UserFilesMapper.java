package com.myweb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myweb.pojo.UserFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFilesMapper extends BaseMapper<UserFiles> {

    //基本CRUD已透過Mybatis Plus 配置完成

}
