// TournamentRegistrationMapper.java
package com.system.mapper;

import com.system.entity.TournamentRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TournamentRegistrationMapper {

    int insertRegistration(TournamentRegistration registration);
    
    TournamentRegistration selectById(@Param("id") UUID id);
    
    TournamentRegistration selectByStudentAndTournament(@Param("studentId") UUID studentId, 
                                                      @Param("tournamentId") UUID tournamentId);
    
    List<TournamentRegistration> selectByTournamentId(@Param("tournamentId") UUID tournamentId);
    
    int updateFeePaidStatus(@Param("id") UUID id, @Param("feePaid") Boolean feePaid);
    
    int countRegistrationsByTournament(@Param("tournamentId") UUID tournamentId);
}