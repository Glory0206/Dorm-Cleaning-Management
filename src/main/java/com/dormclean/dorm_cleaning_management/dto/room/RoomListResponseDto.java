package com.dormclean.dorm_cleaning_management.dto.room;

import com.dormclean.dorm_cleaning_management.entity.enums.RoomStatus;

import java.time.Instant;

public record RoomListResponseDto(
        String dormCode,
        Integer floor,
        String roomNumber,
        RoomStatus roomStatus,
        Instant cleanedAt,
        Instant checkInAt,
        Instant checkOutAt) {
}
