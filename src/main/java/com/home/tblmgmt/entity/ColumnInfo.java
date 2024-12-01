package com.home.tblmgmt.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ColumnInfo {
    private String columnPhysicalName;
    private String columnLogicalName;
    private String dataType;
    private boolean isNullable;
    private boolean isPrimaryKey;
}
