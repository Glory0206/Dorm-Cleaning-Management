package com.dormclean.dorm_cleaning_management.dto;

public record CreateRoomRequestDto(
        long dormId,
        Integer floor,
        String roomNumber) {
}