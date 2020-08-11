package com.myweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myweb.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    //基本CRUD已透過Mybatis Plus 配置完成

}
