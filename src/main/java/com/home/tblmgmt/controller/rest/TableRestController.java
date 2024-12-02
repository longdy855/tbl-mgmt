package com.home.tblmgmt.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.tblmgmt.entity.ColumnInfo;
import com.home.tblmgmt.entity.TableInfo;
import com.home.tblmgmt.service.TableService;

@RestController
@RequestMapping("/api/v1")
public class TableRestController {
    
    private final TableService tableService;

    public TableRestController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/tables")
    public List<TableInfo> getTables(@RequestParam(required = false) String search) {
        if (null == search) {
            search = "";
        }
        return tableService.getAllTables(search);
    }

    @GetMapping("/table-detail/{name}")
    public List<ColumnInfo> tableDetails(@PathVariable String name) {
       return tableService.getTableDetails(name);
    }

    @PostMapping("/table-detail")
    public ResponseEntity<String> saveTableDetail(@RequestBody List<ColumnInfo> columnInfo) {
        columnInfo.forEach(System.out::println);

        // Return a response to the client
        return ResponseEntity.ok("Data received successfully!");
    }

    
}
