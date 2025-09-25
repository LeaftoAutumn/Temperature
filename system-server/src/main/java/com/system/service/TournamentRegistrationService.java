// TournamentRegistrationService.java
package com.system.service;

import com.system.dto.TournamentRegistrationDTO;
import com.system.vo.TournamentRegistrationResponseVO;

public interface TournamentRegistrationService {
    
    TournamentRegistrationResponseVO registerForTournament(TournamentRegistrationDTO registrationDTO, 
                                                          String currentUserId);
}