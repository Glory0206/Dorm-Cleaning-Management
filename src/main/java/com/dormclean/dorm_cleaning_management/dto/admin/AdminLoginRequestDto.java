package com.dormclean.dorm_cleaning_management.dto.admin;

public record AdminLoginRequestDto(
        String userId,
        String password
) {
}
