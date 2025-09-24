package com.system.service.impl;

import com.system.entity.Campus;
import com.system.mapper.CampusMapper;
import com.system.service.CampusService;
import com.system.vo.BriefCampusVO;
import com.system.vo.CompleteCampusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampusServiceImpl implements CampusService {

    @Autowired
    private CampusMapper campusMapper;

    @Override
    public List<BriefCampusVO> listAll() {
        List<Campus> campusList = campusMapper.queryAll();

        return campusList.stream().map(campus -> BriefCampusVO.builder()
                .campusId(String.valueOf(campus.getCampusId()))
                .name(campus.getName())
                .center(campus.getCenter())
                .build())
                .collect(Collectors.toCollection(() -> new ArrayList<>(campusList.size())));
    }

    @Override
    public CompleteCampusVO getById(Long campusId) {
        Campus campus = campusMapper.queryById(campusId);

        return CompleteCampusVO.builder()
                .campusId(String.valueOf(campus.getCampusId()))
                .name(campus.getName())
                .address(campus.getAddress())
                .phone(campus.getPhone())
                .center(campus.getCenter())
                .build();
    }
}
