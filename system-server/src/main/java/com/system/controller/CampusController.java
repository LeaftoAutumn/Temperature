// CampusController.java (更新)
package com.system.controller;

import com.system.dto.CampusCreateRequestDTO;
import com.system.dto.CampusUpdateRequestDTO;
import com.system.service.CampusService;
import com.system.vo.CampusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campuses")
@Slf4j
@Api(tags = "校区管理")
public class CampusController {

    @Autowired
    private CampusService campusService;

    @GetMapping
    @ApiOperation("获取所有校区列表")
    public List<CampusVO> listCampuses() {
        log.info("获取所有校区列表");
        return campusService.listAllCampuses();
    }

    @PostMapping
    @ApiOperation("创建新校区")
    @ResponseStatus(HttpStatus.CREATED)
    public CampusVO createCampus(@Valid @RequestBody CampusCreateRequestDTO createRequestDTO) {
        log.info("创建新校区: {}", createRequestDTO.getName());
        return campusService.createCampus(createRequestDTO);
    }

    @GetMapping("/{campusId}")
    @ApiOperation("获取指定校区详情")
    public CampusVO getCampus(@PathVariable String campusId) {
        log.info("获取校区详情: {}", campusId);
        return campusService.getCampusById(campusId);
    }

    @PutMapping("/{campusId}")
    @ApiOperation("更新校区信息")
    public CampusVO updateCampus(@PathVariable String campusId, 
                               @Valid @RequestBody CampusUpdateRequestDTO updateRequestDTO) {
        log.info("更新校区信息: {}", campusId);
        return campusService.updateCampus(campusId, updateRequestDTO);
    }
}