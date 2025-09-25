// TournamentGroupController.java
package com.system.controller;

import com.system.service.TournamentGroupService;
import com.system.vo.TournamentGroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments/{tournamentId}/groups")
@Slf4j
@Api(tags = "月赛管理")
public class TournamentGroupController {

    @Autowired
    private TournamentGroupService groupService;

    @GetMapping
    @ApiOperation("获取月赛分组情况")
    public List<TournamentGroupVO> getTournamentGroups(@PathVariable String tournamentId) {
        log.info("获取月赛分组情况: tournamentId={}", tournamentId);
        return groupService.getTournamentGroups(tournamentId);
    }
}