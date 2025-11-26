package com.dormclean.dorm_cleaning_management.controller;

import com.dormclean.dorm_cleaning_management.dto.CreateDormRequest;
import com.dormclean.dorm_cleaning_management.dto.CreateRoomRequest;
import com.dormclean.dorm_cleaning_management.entity.Dorm;
import com.dormclean.dorm_cleaning_management.entity.Room;
import com.dormclean.dorm_cleaning_management.service.DormService;
import com.dormclean.dorm_cleaning_management.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DormController {

    private final DormService dormitoryService;
    private final RoomService roomService;

    // 기숙사 생성
    @PostMapping("/dormitories")
    public ResponseEntity<Long> createDormitory(@RequestBody CreateDormRequest request) {
        Dorm dormitory = dormitoryService.createDormitory(
                request.getDormCode(),
                request.getDormName());
        return ResponseEntity.ok(dormitory.getId());
    }

    // 방 생성
    @PostMapping("/rooms")
    public ResponseEntity<Long> createRoom(@RequestBody CreateRoomRequest request) {
        Room room = roomService.createRoom(
                request.getDormCode(),
                request.getFloor(),
                request.getRoomNumber());
        return ResponseEntity.ok(room.getId());
    }
}
