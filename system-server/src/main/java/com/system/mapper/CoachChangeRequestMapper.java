// CoachChangeRequestMapper.java
package com.system.mapper;

import com.system.entity.CoachChangeRequest;
import com.system.vo.CoachChangeRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CoachChangeRequestMapper {

    int insertChangeRequest(CoachChangeRequest changeRequest);
    
    CoachChangeRequest selectById(@Param("id") UUID id);
    
    List<CoachChangeRequest> selectByStudentId(@Param("studentId") UUID studentId);
    
    int updateChangeRequestStatus(@Param("id") UUID id, @Param("status") String status);
}