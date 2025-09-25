package com.system.controller;

import com.system.dto.AvailableTimeSlotQueryDTO;
import com.system.service.ReservationService;
import com.system.vo.AvailableTimeSlotVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/time-slots")
@Slf4j
@Api(tags = "时段管理")
public class TimeSlotController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/available")
    @ApiOperation("查询可用时段")
    public List<AvailableTimeSlotVO> getAvailableTimeSlots(@Valid AvailableTimeSlotQueryDTO queryDTO) {
        log.info("查询可用时段: coachId={}, date={}", queryDTO.getCoachId(), queryDTO.getDate());
        return reservationService.getAvailableTimeSlots(queryDTO);
    }
}