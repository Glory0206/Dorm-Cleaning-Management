package com.dormclean.dorm_cleaning_management.service.qr;

import com.dormclean.dorm_cleaning_management.dto.zipFile.QrGenerationData;
import com.dormclean.dorm_cleaning_management.entity.Dorm;
import com.dormclean.dorm_cleaning_management.entity.QrCode;
import com.dormclean.dorm_cleaning_management.entity.Room;
import com.dormclean.dorm_cleaning_management.repository.DormRepository;
import com.dormclean.dorm_cleaning_management.repository.QrCodeRepository;
import com.dormclean.dorm_cleaning_management.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component // 스프링 빈으로 등록
@RequiredArgsConstructor
public class QrDataProcessor {

    private final DormRepository dormRepository;
    private final RoomRepository roomRepository;
    private final QrCodeRepository qrCodeRepository;

    @Value("${app.host}") // application.yml의 설정값
    private String host;

    @Transactional
    public List<QrGenerationData> prepareQrDataAndSaveBulk(List<String> dormCodes) {
        List<Dorm> dorms = dormRepository.findByDormCodeIn(dormCodes);
        List<Room> allRooms = roomRepository.findByDormIn(dorms);
        List<QrCode> allExistingQrCodes = qrCodeRepository.findByRoomIn(allRooms);

        Map<Long, List<Room>> roomMapByDormId = allRooms.stream()
                .collect(Collectors.groupingBy(room -> room.getDorm().getId()));
        Map<Long, QrCode> qrMapByRoomId = allExistingQrCodes.stream()
                .collect(Collectors.toMap(qr -> qr.getRoom().getId(), qr -> qr));

        List<QrGenerationData> resultData = new ArrayList<>();
        List<QrCode> qrCodesToSave = new ArrayList<>();

        for (Dorm dorm : dorms) {
            List<Room> rooms = roomMapByDormId.getOrDefault(dorm.getId(), Collections.emptyList());
            String dormFolder = dorm.getDormCode() + "/";

            for (Room room : rooms) {
                QrCode qrCode = qrMapByRoomId.get(room.getId());

                if (qrCode != null) {
                    qrCode.refreshUuid(); // 기존 QR은 UUID 갱신
                } else {
                    qrCode = QrCode.builder().room(room).build(); // 없으면 새로 생성
                }
                qrCodesToSave.add(qrCode);

                // 이미지 생성에 필요한 데이터 미리 가공
                String content = String.format("%s/check?token=%s", host, qrCode.getUuid());
                String labelText = String.format("%s동 %s호", dorm.getDormCode(), room.getRoomNumber());
                String fileName = dormFolder + "QR_" + dorm.getDormCode() + "동_" + room.getRoomNumber() + "호.png";

                resultData.add(new QrGenerationData(content, labelText, fileName));
            }
        }
        qrCodeRepository.saveAll(qrCodesToSave);

        return resultData;
    }
}