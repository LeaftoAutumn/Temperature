// CampusServiceImpl.java (更新)
package com.system.service.impl;

import com.system.dto.CampusCreateRequestDTO;
import com.system.dto.CampusQueryDTO;
import com.system.dto.CampusUpdateRequestDTO;
import com.system.entity.Campus;
import com.system.mapper.CampusMapper;
import com.system.mapper.UserMapper;
import com.system.service.CampusService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampusServiceImpl implements CampusService {

    @Autowired
    private CampusMapper campusMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<CampusVO> listAllCampuses() {
        log.info("获取所有校区列表");
        
        List<Campus> campuses = campusMapper.selectAll();
        
        return campuses.stream()
                .map(this::convertToCampusVO)
                .collect(Collectors.toList());
    }

    @Override
    public CampusVO getCampusById(String campusId) {
        log.info("获取校区详情: {}", campusId);
        
        Campus campus = campusMapper.selectById(UUID.fromString(campusId));
        if (campus == null) {
            throw new RuntimeException("校区不存在");
        }
        
        return convertToCampusVO(campus);
    }

    @Transactional
    @Override
    public CampusVO createCampus(CampusCreateRequestDTO createRequestDTO) {
        log.info("创建新校区: {}", createRequestDTO.getName());


        
        // 检查校区名称是否已存在
        if (campusMapper.existsByName(createRequestDTO.getName())) {
            throw new RuntimeException("校区名称已存在");
        }
        
        Campus campus = Campus.builder()
                .campusId(UUID.randomUUID())
                .name(createRequestDTO.getName())
                .address(createRequestDTO.getAddress())
                .phone(createRequestDTO.getContactPhone())
                .center(createRequestDTO.getIsCenter() != null ? createRequestDTO.getIsCenter() : false)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        campusMapper.insertCampus(campus);
        
        return convertToCampusVO(campus);
    }

    @Transactional
    @Override
    public CampusVO updateCampus(String campusId, CampusUpdateRequestDTO updateRequestDTO) {
        log.info("更新校区信息: {}", campusId);
        
        Campus existingCampus = campusMapper.selectById(UUID.fromString(campusId));
        if (existingCampus == null) {
            throw new RuntimeException("校区不存在");
        }
        
        // 如果修改了名称，检查新名称是否已存在
        if (updateRequestDTO.getName() != null && 
            !updateRequestDTO.getName().equals(existingCampus.getName()) &&
            campusMapper.existsByName(updateRequestDTO.getName())) {
            throw new RuntimeException("校区名称已存在");
        }
        
        // 更新字段
        if (updateRequestDTO.getName() != null) {
            existingCampus.setName(updateRequestDTO.getName());
        }
        if (updateRequestDTO.getAddress() != null) {
            existingCampus.setAddress(updateRequestDTO.getAddress());
        }
        if (updateRequestDTO.getContactPhone() != null) {
            existingCampus.setPhone(updateRequestDTO.getContactPhone());
        }
        if (updateRequestDTO.getIsCenter() != null) {
            existingCampus.setCenter(updateRequestDTO.getIsCenter());
        }
        existingCampus.setUpdateTime(LocalDateTime.now());
        
        campusMapper.updateCampus(existingCampus);
        
        return convertToCampusVO(existingCampus);
    }

    @Override
    public CampusUserPageVO listCampusUsers(String campusId, CampusQueryDTO queryDTO) {
        log.info("获取校区用户列表: {}", campusId);
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询用户列表
        List<CampusUserVO> users = userMapper.selectSpecialUsersByCampusIdAndRole(UUID.fromString(campusId), queryDTO.getRole());
        
        // 查询总数
        long total = campusMapper.countUsersByCampusId(
            UUID.fromString(campusId), queryDTO.getRole());
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return CampusUserPageVO.builder()
                .users(users)
                .pagination(pagination)
                .build();
    }
    
    private CampusVO convertToCampusVO(Campus campus) {
        return CampusVO.builder()
                .id(campus.getCampusId())
                .name(campus.getName())
                .address(campus.getAddress())
                .contactPhone(campus.getPhone())
                .isCenter(campus.getCenter())
                .createTime(campus.getCreateTime())
                .build();
    }
}