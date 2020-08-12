package com.myweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myweb.pojo.UserStore;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoreMapper extends BaseMapper<UserStore> {

    //基本CRUD已透過Mybatis Plus 配置完成

}
