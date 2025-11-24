package com.dormclean.dorm_cleaning_management.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QrCodeServiceImp implements QrCodeService {
    @Override
    public byte[] generateQrCode(String content, int width, int height){
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("QR 코드 생성 중 오류 발생", e);
        }
    }
}
