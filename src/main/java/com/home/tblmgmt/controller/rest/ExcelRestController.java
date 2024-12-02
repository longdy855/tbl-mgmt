package com.home.tblmgmt.controller.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.tblmgmt.entity.ColumnInfo;
import com.home.tblmgmt.entity.User;
import com.home.tblmgmt.service.ExcelService;
import com.home.tblmgmt.service.TableService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ExcelRestController {
    
    private ExcelService excelService;
    private final TableService tableService;

    @GetMapping("/download/users")
    public ResponseEntity<byte[]> downloadExcel() {
        // Sample data
        List<User> users = Arrays.asList(
            new User(1L, "Alice", "alice@example.com"),
            new User(2L, "Bob", "bob@example.com")
        );

        byte[] excelContent = excelService.generateExcel(users);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "users.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }

    
    @GetMapping("/download/table-details/{tableName}")
    public ResponseEntity<byte[]> downloadTableDetail(@PathVariable String tableName) {
        // Sample data
        List<ColumnInfo> columnInfos = tableService.getTableDetails(tableName);

        byte[] excelContent = excelService.generateTableDetailExcel(columnInfos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", tableName + ".xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }
}
