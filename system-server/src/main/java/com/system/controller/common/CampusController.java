package com.system.controller.common;

import com.system.result.Result;
import com.system.service.CampusService;
import com.system.vo.BriefCampusVO;
import com.system.vo.CompleteCampusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("commonCampusController")
@RequestMapping("/common/campus")
@Slf4j
@Api(tags = "公共校区管理")
public class CampusController {

    @Autowired
    private CampusService campusService;

    /**
     * 查询校区列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询校区列表")
    public Result<List<BriefCampusVO>> list() {
        log.info("查询校区列表");

        List<BriefCampusVO> campuses = campusService.listAll();

        return Result.success(campuses);
    }

    /**
     * 根据ID查询校区
     *
     * @param campusId 校区ID
     * @return
     */
    @GetMapping("/{campusId}")
    @ApiOperation("根据ID查询校区")
    public Result<CompleteCampusVO> getById(@PathVariable Long campusId) {
        log.info("根据ID查询校区: {}", campusId);

        CompleteCampusVO campus = campusService.getById(campusId);

        return Result.success(campus);
    }
}
