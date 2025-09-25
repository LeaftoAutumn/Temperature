// TournamentServiceImpl.java
package com.system.service.impl;

import com.system.dto.TournamentCreateDTO;
import com.system.dto.TournamentUpdateDTO;
import com.system.entity.Tournament;
import com.system.mapper.TournamentMapper;
import com.system.service.TournamentService;
import com.system.vo.TournamentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentMapper tournamentMapper;

    @Override
    public List<TournamentVO> getTournaments(String status) {
        log.info("获取月赛列表: status={}", status);
        return tournamentMapper.selectTournaments(status);
    }

    @Override
    public TournamentVO getTournamentById(String tournamentId) {
        log.info("获取月赛详情: tournamentId={}", tournamentId);
        
        UUID tournamentUUID = UUID.fromString(tournamentId);
        Tournament tournament = tournamentMapper.selectById(tournamentUUID);
        
        if (tournament == null) {
            throw new RuntimeException("月赛不存在");
        }
        
        return convertToVO(tournament);
    }

    @Transactional
    @Override
    public TournamentVO createTournament(TournamentCreateDTO createDTO, String currentUserId) {
        log.info("创建月赛: name={}, eventDate={}", createDTO.getName(), createDTO.getEventDate());
        
        // 验证权限（只有管理员可以创建月赛）
        // 这里需要实现权限验证逻辑
        
        // 检查月赛名称和日期是否重复
        if (tournamentMapper.existsByNameAndDate(createDTO.getName(), createDTO.getEventDate().toString())) {
            throw new RuntimeException("相同名称和日期的月赛已存在");
        }
        
        // 创建月赛
        Tournament tournament = Tournament.builder()
                .id(UUID.randomUUID())
                .name(createDTO.getName())
                .eventDate(createDTO.getEventDate())
                .groupType(createDTO.getGroupType())
                .format(createDTO.getFormat())
                .status("upcoming")
                .entryFee(createDTO.getEntryFee())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        tournamentMapper.insertTournament(tournament);
        
        return convertToVO(tournament);
    }

    @Transactional
    @Override
    public TournamentVO updateTournament(String tournamentId, TournamentUpdateDTO updateDTO, String currentUserId) {
        log.info("更新月赛信息: tournamentId={}", tournamentId);
        
        // 验证权限（只有管理员可以更新月赛）
        // 这里需要实现权限验证逻辑
        
        UUID tournamentUUID = UUID.fromString(tournamentId);
        Tournament tournament = tournamentMapper.selectById(tournamentUUID);
        
        if (tournament == null) {
            throw new RuntimeException("月赛不存在");
        }
        
        // 更新字段
        if (updateDTO.getName() != null) {
            tournament.setName(updateDTO.getName());
        }
        if (updateDTO.getEventDate() != null) {
            tournament.setEventDate(updateDTO.getEventDate());
        }
        if (updateDTO.getGroupType() != null) {
            tournament.setGroupType(updateDTO.getGroupType());
        }
        if (updateDTO.getFormat() != null) {
            tournament.setFormat(updateDTO.getFormat());
        }
        if (updateDTO.getStatus() != null) {
            tournament.setStatus(updateDTO.getStatus());
        }
        if (updateDTO.getEntryFee() != null) {
            tournament.setEntryFee(updateDTO.getEntryFee());
        }
        
        tournament.setUpdateTime(LocalDateTime.now());
        tournamentMapper.updateTournament(tournament);
        
        return convertToVO(tournament);
    }
    
    private TournamentVO convertToVO(Tournament tournament) {
        return TournamentVO.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .eventDate(tournament.getEventDate())
                .groupType(tournament.getGroupType())
                .format(tournament.getFormat())
                .status(tournament.getStatus())
                .entryFee(tournament.getEntryFee())
                .createdTime(tournament.getCreateTime())
                .build();
    }
}