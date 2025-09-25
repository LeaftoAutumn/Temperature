package com.system.controller;

import com.system.dto.TimetableQueryDTO;
import com.system.service.ReservationService;
import com.system.vo.TimetableItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/timetable")
@Slf4j
@Api(tags = "课表管理")
public class TimetableController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    @ApiOperation("获取用户课表")
    public List<TimetableItemVO> getUserTimetable(@PathVariable String userId,
                                                @Valid TimetableQueryDTO queryDTO,
                                                HttpServletRequest request) {
        log.info("获取用户课表: userId={}", userId);
        return reservationService.getUserTimetable(userId, queryDTO);
    }
}