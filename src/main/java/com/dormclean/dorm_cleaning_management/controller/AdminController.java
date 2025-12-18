package com.dormclean.dorm_cleaning_management.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dormclean.dorm_cleaning_management.dto.admin.AdminLoginRequestDto;
import com.dormclean.dorm_cleaning_management.service.admin.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "유저 관리 API", description = "유저 생성 및 삭제 처리 API")
public class AdminController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody AdminLoginRequestDto dto) {
        userService.create(dto.username(), dto.password());

        return "redirect:/";
    }

}
