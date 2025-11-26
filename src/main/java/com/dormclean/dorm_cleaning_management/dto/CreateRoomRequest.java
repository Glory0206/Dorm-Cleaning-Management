package com.dormclean.dorm_cleaning_management.dto;

import lombok.Getter;

@Getter
public class CreateRoomRequest {
    private String dormCode; // 어느 기숙사의 방인지
    private Integer floor;
    private String roomNumber;
}
