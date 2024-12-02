package com.home.tblmgmt.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.home.tblmgmt.entity.ColumnInfo;
import com.home.tblmgmt.entity.TableInfo;
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

    
    public byte[] generateTableDetailExcel(List<ColumnInfo> columnInfos) {
        try (Workbook workbook = new XSSFWorkbook(); 
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users");

            // Header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"No", "Physical Name", "Logical Name", "Data Type", "Nullable", "Primary Key"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data rows
            int rowIdx = 1;
            for (ColumnInfo columnInfo : columnInfos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(columnInfo.getRowNo());
                row.createCell(1).setCellValue(columnInfo.getColumnPhysicalName());
                row.createCell(2).setCellValue(columnInfo.getColumnLogicalName());
                row.createCell(3).setCellValue(columnInfo.getDataType());
                // row.createCell(4).setCellValue(columnInfo.isNullable());
                row.createCell(5).setCellValue(columnInfo.isPrimaryKey());
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error while generating Excel file", e);
        }
    }
}
