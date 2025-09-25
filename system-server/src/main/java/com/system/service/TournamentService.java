// TournamentService.java
package com.system.service;

import com.system.dto.TournamentCreateDTO;
import com.system.dto.TournamentUpdateDTO;
import com.system.vo.TournamentVO;

import java.util.List;

public interface TournamentService {
    
    List<TournamentVO> getTournaments(String status);
    
    TournamentVO getTournamentById(String tournamentId);
    
    TournamentVO createTournament(TournamentCreateDTO createDTO, String currentUserId);
    
    TournamentVO updateTournament(String tournamentId, TournamentUpdateDTO updateDTO, String currentUserId);
}