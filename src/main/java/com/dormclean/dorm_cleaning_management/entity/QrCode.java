package com.dormclean.dorm_cleaning_management.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "qr_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String dormName;

    @Column(nullable = false)
    private String roomNumber;

    @Builder
    public QrCode(String dormName, String roomNumber) {
        this.uuid = UUID.randomUUID().toString();
        this.dormName = dormName;
        this.roomNumber = roomNumber;
    }

    public void refreshUuid() {
        this.uuid = UUID.randomUUID().toString();
    }
}
