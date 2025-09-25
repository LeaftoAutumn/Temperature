// TournamentGroupServiceImpl.java
package com.system.service.impl;

import com.system.mapper.TournamentMatchMapper;
import com.system.service.TournamentGroupService;
import com.system.vo.TournamentGroupVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TournamentGroupServiceImpl implements TournamentGroupService {

    @Autowired
    private TournamentMatchMapper matchMapper;

    @Override
    public List<TournamentGroupVO> getTournamentGroups(String tournamentId) {
        log.info("获取月赛分组信息: tournamentId={}", tournamentId);
        
        UUID tournamentUUID = UUID.fromString(tournamentId);
        return matchMapper.selectTournamentGroups(tournamentUUID);
    }
}