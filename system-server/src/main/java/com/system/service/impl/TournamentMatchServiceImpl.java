// TournamentMatchServiceImpl.java
package com.system.service.impl;

import com.system.dto.MatchResultUpdateDTO;
import com.system.entity.TournamentMatch;
import com.system.mapper.TournamentMatchMapper;
import com.system.service.TournamentMatchService;
import com.system.vo.TournamentMatchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TournamentMatchServiceImpl implements TournamentMatchService {

    @Autowired
    private TournamentMatchMapper matchMapper;

    @Override
    public List<TournamentMatchVO> getTournamentMatches(String tournamentId) {
        log.info("获取月赛场次信息: tournamentId={}", tournamentId);
        
        UUID tournamentUUID = UUID.fromString(tournamentId);
        return matchMapper.selectByTournamentId(tournamentUUID);
    }

    @Transactional
    @Override
    public TournamentMatchVO updateMatchResult(String matchId, MatchResultUpdateDTO updateDTO, String currentUserId) {
        log.info("更新比赛结果: matchId={}", matchId);
        
        // 验证权限（只有管理员可以更新比赛结果）
        // 这里需要实现权限验证逻辑
        
        UUID matchUUID = UUID.fromString(matchId);
        TournamentMatch match = matchMapper.selectById(matchUUID);
        
        if (match == null) {
            throw new RuntimeException("比赛记录不存在");
        }
        
        // 验证获胜者ID是否合法
        UUID winnerUUID = UUID.fromString(updateDTO.getWinnerId());
        if (!winnerUUID.equals(match.getPlayer1Id()) && !winnerUUID.equals(match.getPlayer2Id())) {
            throw new RuntimeException("获胜者必须是比赛选手之一");
        }
        
        // 更新比赛结果
        match.setResult(updateDTO.getResult());
        match.setWinnerId(winnerUUID);
        match.setStatus(updateDTO.getStatus());
        match.setUpdateTime(LocalDateTime.now());
        
        matchMapper.updateMatchResult(match);
        
        // 返回更新后的比赛信息
        List<TournamentMatchVO> matches = matchMapper.selectByTournamentId(match.getTournamentId());
        return matches.stream()
                .filter(m -> m.getId().equals(matchId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("比赛信息获取失败"));
    }
}