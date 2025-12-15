package com.dormclean.dorm_cleaning_management.dto;

import java.time.Instant;
import java.util.*;

public record BulkRoomStatusUpdateDto(
        String dormCode,
        List<String> roomNumbers,
        String newRoomStatus,
        Instant time) {
}
