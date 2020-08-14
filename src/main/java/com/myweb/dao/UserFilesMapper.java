package com.myweb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myweb.pojo.UserFiles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserFilesMapper extends BaseMapper<UserFiles> {

    //基本CRUD已透過Mybatis Plus 配置完成

    //找尋指定使用者id和種類的檔案
    @Select("select * from user_files where id = #{id} and type = #{type}")
    public ArrayList<UserFiles> selectFilesByIdAndType(@Param("id")int id,@Param("type")int type);

    //找尋指定使用者id的所有檔案
    @Select("select * from user_files where id = #{id}")
    public ArrayList<UserFiles> selectAllFilesById(@Param("id")int id);

    //找尋指定檔案名稱的檔案
    @Select("select * from user_files where id = #{id} and file_name = #{fileName}")
    public UserFiles selectFileByIdAndFileName(@Param("id")int id,@Param("fileName")String fileName);

    //刪除指定檔案名稱的檔案
    @Delete("delete from user_files where id = #{id} and file_name = #{fileName}")
    public boolean deleteFileByIdAndFileName(@Param("id")int id,@Param("fileName")String fileName);

}
