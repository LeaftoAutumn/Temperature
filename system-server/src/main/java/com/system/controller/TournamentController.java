// TournamentController.java
package com.system.controller;

import com.system.dto.TournamentCreateDTO;
import com.system.dto.TournamentUpdateDTO;
import com.system.service.TournamentService;
import com.system.vo.TournamentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tournaments")
@Slf4j
@Api(tags = "月赛管理")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    @ApiOperation("获取月赛列表")
    public List<TournamentVO> getTournaments(@RequestParam(required = false) String status) {
        log.info("获取月赛列表: status={}", status);
        return tournamentService.getTournaments(status);
    }

    @PostMapping
    @ApiOperation("创建月赛（仅管理员）")
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentVO createTournament(@Valid @RequestBody TournamentCreateDTO createDTO,
                                       HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("创建月赛: currentUserId={}, userRole={}", currentUserId, userRole);
        
        // 权限验证
        if (!"super_admin".equals(userRole) && !"campus_admin".equals(userRole)) {
            throw new RuntimeException("权限不足");
        }
        
        return tournamentService.createTournament(createDTO, currentUserId);
    }

    @GetMapping("/{tournamentId}")
    @ApiOperation("获取月赛详情")
    public TournamentVO getTournamentById(@PathVariable String tournamentId) {
        log.info("获取月赛详情: tournamentId={}", tournamentId);
        return tournamentService.getTournamentById(tournamentId);
    }

    @PutMapping("/{tournamentId}")
    @ApiOperation("更新月赛信息（仅管理员）")
    public TournamentVO updateTournament(@PathVariable String tournamentId,
                                       @Valid @RequestBody TournamentUpdateDTO updateDTO,
                                       HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("更新月赛信息: tournamentId={}, currentUserId={}, userRole={}", 
                tournamentId, currentUserId, userRole);
        
        // 权限验证
        if (!"super_admin".equals(userRole) && !"campus_admin".equals(userRole)) {
            throw new RuntimeException("权限不足");
        }
        
        return tournamentService.updateTournament(tournamentId, updateDTO, currentUserId);
    }
}