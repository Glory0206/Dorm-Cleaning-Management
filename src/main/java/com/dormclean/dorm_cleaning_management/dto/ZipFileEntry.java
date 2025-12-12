package com.dormclean.dorm_cleaning_management.dto;

public record ZipFileEntry(
        String fileName,     // ex) 기숙사A/QR_A동_101호.png
        byte[] imageBytes    // 실제 생성된 QR 코드 이미지 데이터
) {}
