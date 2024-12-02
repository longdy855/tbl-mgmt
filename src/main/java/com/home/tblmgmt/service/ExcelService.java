package com.home.tblmgmt.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.home.tblmgmt.entity.User;

@Service
public class ExcelService {

    public byte[] generateExcel(List<User> users) {
        try (Workbook workbook = new XSSFWorkbook(); 
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users");

            // Header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Name", "Email"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data rows
            int rowIdx = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getEmail());
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error while generating Excel file", e);
        }
    }
}
