package com.home.tblmgmt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.tblmgmt.entity.ColumnInfo;
import com.home.tblmgmt.entity.TableInfo;
import com.home.tblmgmt.service.TableService;

@Controller
public class TableController {
    
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/")
    public String tableList(@RequestParam(value = "search", required = false) String search, Model model) {
    // public String tableList(Model model) {
        if (null == search) {
            search = "";
        }
        List<TableInfo> tables = tableService.getAllTables(search);
        model.addAttribute("tables", tables);
        model.addAttribute("search", search);
        return "table-list";    
    }

    @GetMapping("/table-details")
    public String tableDetails(@RequestParam String tableName, Model model) {
        List<ColumnInfo> columns = tableService.getTableDetails(tableName);
        model.addAttribute("columns", columns);
        model.addAttribute("tableName", tableName);
        return "table-details";
    }
}
