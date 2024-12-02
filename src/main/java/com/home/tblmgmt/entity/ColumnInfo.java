package com.home.tblmgmt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ColumnInfo {
    private int rowNo;
    private String columnPhysicalName;
    private String columnLogicalName;
    private String dataType;
    // private boolean isNullable;
    private boolean isPrimaryKey;
    private String codeDesc;


    public boolean isSame(ColumnInfo other) {
        return this.rowNo == other.rowNo &&
               this.columnPhysicalName.equals(other.columnPhysicalName) &&
               this.columnLogicalName.equals(other.columnLogicalName) &&
               this.dataType.equals(other.dataType) &&
               this.isPrimaryKey == other.isPrimaryKey;
    }

}
