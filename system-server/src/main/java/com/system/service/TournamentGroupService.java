// TournamentGroupService.java
package com.system.service;

import com.system.vo.TournamentGroupVO;

import java.util.List;

public interface TournamentGroupService {
    
    List<TournamentGroupVO> getTournamentGroups(String tournamentId);
}