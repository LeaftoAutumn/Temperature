package com.system.mapper;

import com.system.entity.User;
import com.system.vo.CampusUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {

    User selectByUsername(@Param("username") String username);

    User selectById(@Param("id") UUID id);

    int insertUser(User user);

    boolean existsByUsername(@Param("username") String username);

    boolean existsByPhone(@Param("phone") String phone);

    List<User> selectUsersByCampusId(@Param("campusId") UUID campusId);

    int updateUser(User user);

    List<CampusUserVO> selectSpecialUsersByCampusIdAndRole(@Param("campusId") UUID campusId,
                                             @Param("role") String role, long offset, int limit);
}