// TournamentMatchController.java
package com.system.controller;

import com.system.dto.MatchResultUpdateDTO;
import com.system.service.TournamentMatchService;
import com.system.vo.TournamentMatchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tournaments/{tournamentId}/matches")
@Slf4j
@Api(tags = "月赛管理")
public class TournamentMatchController {

    @Autowired
    private TournamentMatchService matchService;

    @GetMapping
    @ApiOperation("获取月赛场次列表")
    public List<TournamentMatchVO> getTournamentMatches(@PathVariable String tournamentId) {
        log.info("获取月赛场次列表: tournamentId={}", tournamentId);
        return matchService.getTournamentMatches(tournamentId);
    }
}

// 单独的MatchController用于更新比赛结果
@RestController
@RequestMapping("/matches")
@Slf4j
@Api(tags = "月赛管理")
class MatchController {

    @Autowired
    private TournamentMatchService matchService;

    @PutMapping("/{matchId}")
    @ApiOperation("更新比赛结果（仅管理员）")
    public TournamentMatchVO updateMatchResult(@PathVariable String matchId,
                                             @Valid @RequestBody MatchResultUpdateDTO updateDTO,
                                             HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("更新比赛结果: matchId={}, currentUserId={}, userRole={}", 
                matchId, currentUserId, userRole);
        
        // 权限验证
        if (!"super_admin".equals(userRole) && !"campus_admin".equals(userRole)) {
            throw new RuntimeException("权限不足");
        }
        
        return matchService.updateMatchResult(matchId, updateDTO, currentUserId);
    }
}