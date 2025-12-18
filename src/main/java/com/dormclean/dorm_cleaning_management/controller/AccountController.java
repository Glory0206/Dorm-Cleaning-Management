package com.dormclean.dorm_cleaning_management.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dormclean.dorm_cleaning_management.dto.admin.AccountListResponseDto;
import com.dormclean.dorm_cleaning_management.entity.enums.UserRole;
import com.dormclean.dorm_cleaning_management.service.admin.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin/api")
@RequiredArgsConstructor
@Tag(name = "유저 관리 API", description = "유저 생성 및 삭제 처리 API")
public class AccountController {
    private final UserService userService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountListResponseDto>> getAdminAccounts() {
        List<AccountListResponseDto> dtoList = userService.AllAdminAccounts(UserRole.ADMIN);
        return ResponseEntity.ok(dtoList);
    }

}
