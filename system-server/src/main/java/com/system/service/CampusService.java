package com.system.service;

import com.system.dto.BriefCampusDTO;
import com.system.vo.BriefCampusVO;
import com.system.vo.CompleteCampusVO;

import java.util.List;

public interface CampusService {
    List<BriefCampusVO> listAll();

    CompleteCampusVO getById(Long campusId);

    CompleteCampusVO createCampus(BriefCampusDTO briefCampusDTO);
}
