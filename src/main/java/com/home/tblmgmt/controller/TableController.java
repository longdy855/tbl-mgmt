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
    public String tableList() {
        return "table-list";    
    }

    // @GetMapping("/table-details")
    // public String tableDetails() {
    //     return "table-details";
    // }


    @GetMapping("/table-details")
    public String tableDetails(@RequestParam String tableName, @RequestParam String logicalName,Model model) {
        model.addAttribute("tableName", tableName);
        model.addAttribute("logicalName", logicalName);
        return "table-details";
    }
}
