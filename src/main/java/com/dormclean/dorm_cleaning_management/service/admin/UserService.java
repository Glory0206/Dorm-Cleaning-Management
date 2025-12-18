package com.dormclean.dorm_cleaning_management.service.admin;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dormclean.dorm_cleaning_management.entity.AdminUser;

public interface UserService {
    public AdminUser create(String username, String password);

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;
}
