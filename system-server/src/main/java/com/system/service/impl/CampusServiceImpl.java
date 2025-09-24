package com.system.service.impl;

import com.system.dto.BriefCampusDTO;
import com.system.entity.Campus;
import com.system.mapper.CampusMapper;
import com.system.service.CampusService;
import com.system.vo.BriefCampusVO;
import com.system.vo.CompleteCampusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
                .createTime(String.valueOf(campus.getCreateTime()))
                .build();
    }

    @Override
    public CompleteCampusVO createCampus(BriefCampusDTO briefCampusDTO) {
        Campus campus = Campus.builder()
                .campusId(UUID.randomUUID())
                .name(briefCampusDTO.getName())
                .address(briefCampusDTO.getAddress())
                .phone(briefCampusDTO.getPhone())
                .center(briefCampusDTO.getCenter())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();

        return campusMapper.createCampus(campus);
    }
}
