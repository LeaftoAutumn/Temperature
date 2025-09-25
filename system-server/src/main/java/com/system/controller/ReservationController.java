package com.system.controller;

import com.system.dto.CreateReservationRequestDTO;
import com.system.dto.ReservationQueryDTO;
import com.system.dto.UpdateReservationStatusDTO;
import com.system.service.ReservationService;
import com.system.vo.ReservationDetailVO;
import com.system.vo.ReservationPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/reservations")
@Slf4j
@Api(tags = "预约管理")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @ApiOperation("创建课程预约")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDetailVO createReservation(@Valid @RequestBody CreateReservationRequestDTO createRequestDTO, 
                                               HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("创建课程预约: currentUserId={}", currentUserId);
        return reservationService.createReservation(createRequestDTO);
    }

    @GetMapping
    @ApiOperation("获取预约列表")
    public ReservationPageVO listReservations(@Valid ReservationQueryDTO queryDTO, HttpServletRequest request) {
        return reservationService.listReservations(queryDTO);
    }

    @PutMapping("/{reservationId}")
    @ApiOperation("处理预约（确认/拒绝）")
    public ReservationDetailVO updateReservationStatus(@PathVariable String reservationId,
                                                     @Valid @RequestBody UpdateReservationStatusDTO updateDTO,
                                                     HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("处理预约: reservationId={}, currentUserId={}", reservationId, currentUserId);
        return reservationService.updateReservationStatus(reservationId, updateDTO, currentUserId);
    }
}