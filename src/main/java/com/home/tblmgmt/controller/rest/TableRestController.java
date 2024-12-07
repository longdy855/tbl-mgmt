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
        // columnInfo.forEach(info -> System.print.out);
        return ResponseEntity.ok("Data received successfully!");
    }

    @PostMapping("/table-detail-remove")
    public ResponseEntity<String> removeTableDetail(@RequestBody List<ColumnInfo> columnInfo,  @RequestParam("tableName") String tableName) {
        if (columnInfo == null || columnInfo.isEmpty()) {
            return ResponseEntity.badRequest().body("No columns provided to remove.");
        }

        for (ColumnInfo data : columnInfo) {
            String columnPhysicalName = data.getColumnPhysicalName();

            if (columnPhysicalName == null || columnPhysicalName.trim().isEmpty()) {
                //logger.warning("Skipping column with empty or null physical name.");
                continue; // Skip columns without a valid name
            }

            try {
                //logger.info("Attempting to remove column: " + columnPhysicalName);
                // Call service to remove column from the table
                tableService.removeColumn(tableName, columnPhysicalName);
                //logger.info("Successfully removed column: " + columnPhysicalName);
            } catch (Exception e) {
                //logger.severe("Error removing column " + columnPhysicalName + ": " + e.getMessage());
                return ResponseEntity.status(500).body("Error removing column: " + columnPhysicalName);
            }
        }

        return ResponseEntity.ok("Columns removed successfully.");
    }
}
