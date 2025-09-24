package com.system.mapper;

import com.system.entity.Campus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CampusMapper {

    @Select("select * from campus")
    List<Campus> queryAll();

    @Select("select * from campuses where id = #{campusId}")
    Campus queryById(Long campusId);
}
