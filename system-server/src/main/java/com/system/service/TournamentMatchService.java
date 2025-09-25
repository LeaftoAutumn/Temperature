// TournamentMatchService.java
package com.system.service;

import com.system.dto.MatchResultUpdateDTO;
import com.system.vo.TournamentMatchVO;

import java.util.List;

public interface TournamentMatchService {
    
    List<TournamentMatchVO> getTournamentMatches(String tournamentId);
    
    TournamentMatchVO updateMatchResult(String matchId, MatchResultUpdateDTO updateDTO, String currentUserId);
}