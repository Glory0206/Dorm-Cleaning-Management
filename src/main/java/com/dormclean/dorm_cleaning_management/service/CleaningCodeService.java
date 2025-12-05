package com.dormclean.dorm_cleaning_management.service;

import com.dormclean.dorm_cleaning_management.dto.CleaningCodeDto;
import com.dormclean.dorm_cleaning_management.dto.GetCleaningCodeResponseDto;
import com.dormclean.dorm_cleaning_management.dto.RegistrationCleaningCodeRequestDto;

public interface CleaningCodeService {
    public void registration(RegistrationCleaningCodeRequestDto dto);

    public void useCleaningCode(CleaningCodeDto dto);

    public GetCleaningCodeResponseDto getCleaningCode();
}
