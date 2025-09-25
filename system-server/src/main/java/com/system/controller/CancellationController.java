package com.system.controller;

import com.system.dto.ConfirmCancellationDTO;
import com.system.dto.CreateCancellationRequestDTO;
import com.system.service.ReservationService;
import com.system.vo.CancellationRemainingVO;
import com.system.vo.CancellationRequestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/cancelations")
@Slf4j
@Api(tags = "取消管理")
public class CancellationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/remaining")
    @ApiOperation("获取本月剩余取消次数")
    public CancellationRemainingVO getRemainingCancellations(HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取本月剩余取消次数: currentUserId={}", currentUserId);
        return reservationService.getRemainingCancellations(currentUserId);
    }

    @PostMapping
    @ApiOperation("发起取消申请")
    @ResponseStatus(HttpStatus.CREATED)
    public CancellationRequestVO createCancellation(@Valid @RequestBody CreateCancellationRequestDTO createDTO,
                                                  HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("发起取消申请: currentUserId={}", currentUserId);
        return reservationService.createCancellation(createDTO, currentUserId);
    }

    @PutMapping("/{cancellationId}")
    @ApiOperation("确认取消申请")
    public CancellationRequestVO confirmCancellation(@PathVariable String cancellationId,
                                                   @Valid @RequestBody ConfirmCancellationDTO confirmDTO,
                                                   HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("确认取消申请: cancellationId={}, currentUserId={}", cancellationId, currentUserId);
        return reservationService.confirmCancellation(cancellationId, confirmDTO, currentUserId);
    }
}