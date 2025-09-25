package com.system.mapper;

import com.system.dto.CoachQueryDTO;
import com.system.entity.Coach;
import com.system.vo.CoachDetailVO;
import com.system.vo.CoachListItemVO;
import com.system.vo.StudentListItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper
public interface CoachMapper {

    Coach selectByUserId(@Param("userId") UUID userId);

    int insertCoach(Coach coach);

    BigDecimal getHourlyRate(@Param("userId") UUID userId);

    int updateCoach(Coach coach);

    CoachDetailVO selectCoachDetail(@Param("coachId") UUID coachId);

    List<CoachListItemVO> selectCoaches(@Param("queryDTO") CoachQueryDTO queryDTO,
                                        @Param("offset") long offset,
                                        @Param("limit") int limit);

    long countCoaches(@Param("queryDTO") CoachQueryDTO queryDTO);

    int countCurrentStudents(@Param("coachId") UUID coachId);

    List<CoachListItemVO> selectStudentCoaches(@Param("studentId") UUID studentId);

    List<StudentListItemVO> selectCoachStudents(@Param("coachId") UUID coachId);
}