package com.myweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myweb.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    //基本CRUD已透過Mybatis Plus 配置完成
    @Select("select * from user where username = #{username}")
    public User selectByUsername(@Param("username")String username);

}
