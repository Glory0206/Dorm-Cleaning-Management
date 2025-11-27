package com.dormclean.dorm_cleaning_management.service;

import com.dormclean.dorm_cleaning_management.entity.Dorm;
import com.dormclean.dorm_cleaning_management.repository.DormRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DormService {

    private final DormRepository dormRepository;

    public Dorm createDorm(String code, String name) {
        Dorm dorm = Dorm.builder()
                .dormCode(code)
                .dormName(name)
                .build();

        return dormRepository.save(dorm);
    }

    @Transactional
    public void deleteDorm(String dormCode) {
        Dorm dorm = dormRepository.findByDormCode(dormCode);
        if (dorm == null) {
            throw new IllegalArgumentException("존재하지 않는 생활관입니다. code=" + dormCode);
        }
        dormRepository.delete(dorm);
    }
}
