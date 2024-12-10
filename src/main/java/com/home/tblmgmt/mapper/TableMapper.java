package com.home.tblmgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.home.tblmgmt.entity.ColumnInfo;
import com.home.tblmgmt.entity.TableInfo;

@Mapper
public interface TableMapper {

    List<TableInfo> getAllTables(String schema, String search);

    List<ColumnInfo> getTableDetails(String schema, String table);

    void renameColumn(String schema, String table, String oldName, String newName);

    void removeColumn(String schema, String table, String column);

    void modifyColumnDataType(String schema, String table, String column, String dataType, int legnth);

    void addColumn(String schema, String table, String column, String dataType, int legnth);

}
