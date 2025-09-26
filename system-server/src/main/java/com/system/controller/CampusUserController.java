// CampusUserController.java
package com.system.controller;

import com.system.annotation.RequireRole;
import com.system.dto.CampusQueryDTO;
import com.system.enumeration.UserRole;
import com.system.service.CampusService;
import com.system.vo.CampusUserPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campuses/{campusId}/users")
@Slf4j
@Api(tags = "校区用户管理")
public class CampusUserController {

    @Autowired
    private CampusService campusService;

    @GetMapping
    @ApiOperation("获取校区用户列表")
    @RequireRole({UserRole.SUPER_ADMIN, UserRole.ADMIN})
    public CampusUserPageVO listCampusUsers(@PathVariable String campusId, 
                                          @ModelAttribute CampusQueryDTO queryDTO) {
        log.info("获取校区用户列表: {}", campusId);
        return campusService.listCampusUsers(campusId, queryDTO);
    }
}