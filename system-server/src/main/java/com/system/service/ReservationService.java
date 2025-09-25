package com.system.service;

import com.system.dto.*;
import com.system.vo.*;

import java.util.List;

public interface ReservationService {
    
    List<AvailableTimeSlotVO> getAvailableTimeSlots(AvailableTimeSlotQueryDTO queryDTO);
    
    ReservationDetailVO createReservation(CreateReservationRequestDTO createRequestDTO);
    
    ReservationPageVO listReservations(ReservationQueryDTO queryDTO, String currentUserId);
    
    ReservationDetailVO updateReservationStatus(String reservationId, UpdateReservationStatusDTO updateDTO, String currentUserId);
    
    List<TimetableItemVO> getUserTimetable(String userId, TimetableQueryDTO queryDTO);
    
    CancellationRemainingVO getRemainingCancellations(String currentUserId);
    
    CancellationRequestVO createCancellation(CreateCancellationRequestDTO createDTO, String currentUserId);
    
    CancellationRequestVO confirmCancellation(String cancellationId, ConfirmCancellationDTO confirmDTO, String currentUserId);
}