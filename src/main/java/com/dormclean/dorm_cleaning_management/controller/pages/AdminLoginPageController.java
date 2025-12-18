package com.dormclean.dorm_cleaning_management.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginPageController {
    @GetMapping("/admin-login")
    public String adminLogin(Model model) {
        return "admin-login";
    }
}
