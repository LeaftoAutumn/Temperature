package com.system.mapper;

import com.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param userList 用户列表
     */
    void createUser(List<User> userList);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);
}
