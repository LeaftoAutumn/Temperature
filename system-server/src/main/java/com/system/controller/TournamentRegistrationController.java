// TournamentRegistrationController.java
package com.system.controller;

import com.system.dto.TournamentRegistrationDTO;
import com.system.service.TournamentRegistrationService;
import com.system.vo.TournamentRegistrationResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/tournament-registrations")
@Slf4j
@Api(tags = "月赛管理")
public class TournamentRegistrationController {

    @Autowired
    private TournamentRegistrationService registrationService;

    @PostMapping
    @ApiOperation("报名月赛")
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentRegistrationResponseVO registerForTournament(
            @Valid @RequestBody TournamentRegistrationDTO registrationDTO,
            HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("报名月赛: currentUserId={}", currentUserId);
        return registrationService.registerForTournament(registrationDTO, currentUserId);
    }
}