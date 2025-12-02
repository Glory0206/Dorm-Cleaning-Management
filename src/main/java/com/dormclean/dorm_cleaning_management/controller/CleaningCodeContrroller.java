package com.dormclean.dorm_cleaning_management.controller;

import com.dormclean.dorm_cleaning_management.dto.CleaningCodeDto;
import com.dormclean.dorm_cleaning_management.dto.CleaningCodeListResponseDto;
import com.dormclean.dorm_cleaning_management.dto.DormListResponseDto;
import com.dormclean.dorm_cleaning_management.dto.RegistrationCleaningCodeRequestDto;
import com.dormclean.dorm_cleaning_management.entity.CleaningCode;
import com.dormclean.dorm_cleaning_management.entity.Dorm;
import com.dormclean.dorm_cleaning_management.repository.CleaningCodeRepository;
import com.dormclean.dorm_cleaning_management.repository.DormRepository;
import com.dormclean.dorm_cleaning_management.service.CleaningCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cleaning-code")
@Tag(name = "청소 인증 코드 API", description = "청소 인증 코드 등록 및 검증 관련 API")
public class CleaningCodeContrroller {
    private final CleaningCodeService cleaningCodeService;
    private final CleaningCodeRepository cleaningCodeRepository;
    private final DormRepository dormRepository;

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationCleaningCodeRequestDto dto) {
        cleaningCodeService.registration(dto.dormCode(), dto.cleaningCode());
    }

    @PostMapping("/use-code")
    public ResponseEntity<String> useCode(@RequestBody CleaningCodeDto dto) {
        cleaningCodeService.useCleaningCode(dto.cleaningCode(), dto.dormCode());

        return ResponseEntity.ok("코드 인증되었습니다.");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCleaningCode(@RequestBody CleaningCodeDto dto) {
        cleaningCodeService.updateCleaningCode(dto.dormCode(), dto.cleaningCode());
        return ResponseEntity.ok("updated");
    }

    @GetMapping("/codes")
    public ResponseEntity<List<CleaningCodeListResponseDto>> getAllCleaningCode() {
        List<CleaningCode> cleaningCodes = cleaningCodeRepository.findAll();

        List<CleaningCodeListResponseDto> dtoList = cleaningCodes.stream()
                .map(d -> new CleaningCodeListResponseDto(d.getCode()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCleaningCode(@RequestBody String dormCode) {
        cleaningCodeService.deleteCleaningCode(dormCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exist/{dormCode}")
    public ResponseEntity<Void> existingCode(@PathVariable String dormCode) {
        Dorm dorm = dormRepository.findByDormCode(dormCode)
                .orElseThrow(() -> new RuntimeException("Dorm not found"));
        Optional<CleaningCode> code = cleaningCodeRepository.findByDorm(dorm);
        if (code.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
