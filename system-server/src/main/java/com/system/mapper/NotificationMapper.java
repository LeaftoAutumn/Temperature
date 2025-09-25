// NotificationMapper.java
package com.system.mapper;

import com.system.dto.NotificationQueryDTO;
import com.system.entity.Notification;
import com.system.vo.NotificationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface NotificationMapper {

    int insertNotification(Notification notification);
    
    Notification selectById(@Param("id") UUID id);
    
    int updateReadStatus(@Param("id") UUID id, @Param("isRead") Boolean isRead);
    
    int batchUpdateReadStatus(@Param("ids") List<UUID> ids, @Param("isRead") Boolean isRead);
    
    int markAllAsRead(@Param("userId") UUID userId);
    
    List<NotificationVO> selectByUserId(@Param("userId") UUID userId,
                                      @Param("queryDTO") NotificationQueryDTO queryDTO,
                                      @Param("offset") long offset,
                                      @Param("limit") int limit);
    
    long countByUserId(@Param("userId") UUID userId,
                     @Param("queryDTO") NotificationQueryDTO queryDTO);
    
    int countUnreadByUserId(@Param("userId") UUID userId);
}