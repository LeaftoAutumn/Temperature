package com.system.controller.admin;

import com.system.dto.BriefCampusDTO;
import com.system.entity.Campus;
import com.system.properties.JwtProperties;
import com.system.service.CampusService;
import com.system.vo.CompleteCampusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminCampusController")
@RequestMapping("/admin/campus")
@Slf4j
@Api(tags = "管理员校区管理")
public class CampusController {

    @Autowired
    private CampusService campusService;

    @PostMapping
    @ApiOperation("新增校区")
    public CompleteCampusVO createCampus(BriefCampusDTO briefCampusDTO) {
        log.info("新增校区");

        return campusService.createCampus(briefCampusDTO);
    }
}
