package com.dormclean.dorm_cleaning_management.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void downloadExcel(HttpServletResponse res) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("RoomInfo");
        sheet.setDefaultColumnWidth(28);

        // Header Font Style
        XSSFFont headerFont = (XSSFFont) workbook.createFont();
        headerFont.setColor(new XSSFColor(new byte[] { (byte) 255, (byte) 255, (byte) 255 }));

        // Header Cell Style
        XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(new XSSFColor(new byte[] { 34, 37, 41 }));
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Body Cell Style
        XSSFCellStyle bodyStyle = (XSSFCellStyle) workbook.createCellStyle();
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle alignStyle = (XSSFCellStyle) workbook.createCellStyle();
        alignStyle.setAlignment(HorizontalAlignment.CENTER);
        alignStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Header 생성
        int rowCount = 0;
        String[] headerNames = { "건물 명", "호실 번호", "**반드시 텍스트로 입력**" };
        Row headerRow = sheet.createRow(rowCount++);

        for (int i = 0; i < headerNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerNames[i]);
            if (i < headerNames.length - 1) {
                cell.setCellStyle(headerStyle);
            }
        }

        // Body 데이터 예시
        String[][] bodyData = {
                { "101", "201" },
        };

        for (String[] rowArr : bodyData) {
            Row row = sheet.createRow(rowCount++);
            for (int i = 0; i < rowArr.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(rowArr[i]);
                cell.setCellStyle(bodyStyle);
            }
        }

        // 조건부 서식
        SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
        ConditionalFormattingRule rule = sheetCF.createConditionalFormattingRule("OR(LEN($A2)>0, LEN($B2)>0)");

        BorderFormatting border = rule.createBorderFormatting();
        border.setBorderLeft(BorderStyle.THIN);
        border.setBorderRight(BorderStyle.THIN);
        border.setBorderTop(BorderStyle.THIN);
        border.setBorderBottom(BorderStyle.THIN);

        CellRangeAddress[] regions = {
                CellRangeAddress.valueOf("A2:B500")
        };
        sheetCF.addConditionalFormatting(regions, rule);

        // Excel 출력
        ServletOutputStream out = res.getOutputStream();
        workbook.write(out);
        workbook.close();
        out.flush();
        out.close();
    }

}
