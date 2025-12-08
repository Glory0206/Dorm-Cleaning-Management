package com.dormclean.dorm_cleaning_management.service;

import jakarta.servlet.http.HttpServletResponse;

public interface ExcelService {
    public void downloadExcel(HttpServletResponse res) throws Exception;
}
