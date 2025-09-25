// TournamentMapper.java
package com.system.mapper;

import com.system.entity.Tournament;
import com.system.vo.TournamentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TournamentMapper {

    int insertTournament(Tournament tournament);
    
    Tournament selectById(@Param("id") UUID id);
    
    int updateTournament(Tournament tournament);
    
    List<TournamentVO> selectTournaments(@Param("status") String status);
    
    boolean existsByNameAndDate(@Param("name") String name, @Param("eventDate") String eventDate);
}