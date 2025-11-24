package com.dormclean.dorm_cleaning_management.service;

public interface QrCodeService {
    public byte[] generateQrCode(String content, int width, int height);
}
