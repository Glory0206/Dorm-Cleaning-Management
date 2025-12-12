package com.dormclean.dorm_cleaning_management.dto.room;

public record CreateRoomRequestDto(
        String dormCode,
        String roomNumber) {
}