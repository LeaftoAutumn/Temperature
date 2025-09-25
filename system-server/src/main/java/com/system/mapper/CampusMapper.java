// CampusMapper.java (更新)
package com.system.mapper;

import com.system.dto.CampusQueryDTO;
import com.system.entity.Campus;
import com.system.vo.CampusUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CampusMapper {

    List<Campus> selectAll();
    
    Campus selectById(@Param("id") UUID id);
    
    int insertCampus(Campus campus);
    
    int updateCampus(Campus campus);
    
    boolean existsByName(@Param("name") String name);
    
    List<CampusUserVO> selectUsersByCampusId(@Param("campusId") UUID campusId, 
                                           @Param("role") String role);
    
    long countUsersByCampusId(@Param("campusId") UUID campusId, 
                            @Param("role") String role);
}