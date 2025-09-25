// TournamentMatchMapper.java
package com.system.mapper;

import com.system.entity.TournamentMatch;
import com.system.vo.TournamentGroupVO;
import com.system.vo.TournamentMatchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TournamentMatchMapper {

    int insertMatch(TournamentMatch match);
    
    TournamentMatch selectById(@Param("id") UUID id);
    
    int updateMatchResult(TournamentMatch match);
    
    List<TournamentMatchVO> selectByTournamentId(@Param("tournamentId") UUID tournamentId);
    
    List<TournamentGroupVO> selectTournamentGroups(@Param("tournamentId") UUID tournamentId);
    
    List<TournamentMatch> selectByTournamentAndGroup(@Param("tournamentId") UUID tournamentId, 
                                                   @Param("groupName") String groupName);
}