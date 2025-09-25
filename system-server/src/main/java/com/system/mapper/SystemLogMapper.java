// SystemLogMapper.java
package com.system.mapper;

import com.system.dto.SystemLogQueryDTO;
import com.system.entity.SystemLog;
import com.system.vo.SystemLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface SystemLogMapper {

    int insertSystemLog(SystemLog systemLog);
    
    List<SystemLogVO> selectSystemLogs(@Param("queryDTO") SystemLogQueryDTO queryDTO,
                                     @Param("offset") long offset,
                                     @Param("limit") int limit);
    
    long countSystemLogs(@Param("queryDTO") SystemLogQueryDTO queryDTO);
}