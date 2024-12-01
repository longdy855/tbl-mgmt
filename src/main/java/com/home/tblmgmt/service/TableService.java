package com.home.tblmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.home.tblmgmt.config.AppConfig;
import com.home.tblmgmt.entity.ColumnInfo;
import com.home.tblmgmt.entity.TableInfo;
import com.home.tblmgmt.mapper.TableMapper;

@Service
public class TableService {

    private final TableMapper tableMapper;
    private final AppConfig appConfig;

    public TableService(TableMapper tableMapper, AppConfig appConfig) {
        this.tableMapper = tableMapper;
        this.appConfig = appConfig;
    }

    public List<TableInfo> getAllTables(String search) {
        return tableMapper.getAllTables(appConfig.getSchemaName(), search);
    }

    public List<ColumnInfo> getTableDetails(String table) {
        return tableMapper.getTableDetails(appConfig.getSchemaName(), table);
    }

    public void renameColumn(String table, String oldName, String newName) {
        tableMapper.renameColumn(appConfig.getSchemaName(), table, oldName, newName);
    }

    public void removeColumn(String table, String column) {
        tableMapper.removeColumn(appConfig.getSchemaName(), table, column);
    }

    public void modifyColumnDataType(String table, String column, String dataType, int length) {
        tableMapper.modifyColumnDataType(appConfig.getSchemaName(), table, column, dataType, length);
    }
}
