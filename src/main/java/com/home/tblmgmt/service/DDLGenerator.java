package com.home.tblmgmt.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class ColumnInfo {
    private String schemaName;
    private String tableName;
    private String logicalName;
    private String physicalName;
    private String dataType;
    private boolean isPrimaryKey;
    private boolean isNotNull;

    // Constructor
    public ColumnInfo(String schemaName, String tableName, String logicalName, String physicalName, String dataType, boolean isPrimaryKey, boolean isNotNull) {
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.logicalName = logicalName;
        this.physicalName = physicalName;
        this.dataType = dataType;
        this.isPrimaryKey = isPrimaryKey;
        this.isNotNull = isNotNull;
    }

    // Getters
    public String getSchemaName() { return schemaName; }
    public String getTableName() { return tableName; }
    public String getLogicalName() { return logicalName; }
    public String getPhysicalName() { return physicalName; }
    public String getDataType() { return dataType; }
    public boolean isPrimaryKey() { return isPrimaryKey; }
    public boolean isNotNull() { return isNotNull; }
}

public class DDLGenerator {

    public static String generateDDL(List<ColumnInfo> columns) {
        if (columns == null || columns.isEmpty()) {
            return "";
        }

        String schemaName = columns.get(0).getSchemaName();
        String tableName = columns.get(0).getTableName();
        StringBuilder ddl = new StringBuilder();

        ddl.append("DROP TABLE IF EXISTS ").append(schemaName).append(".").append(tableName).append(";\n\n");
        ddl.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (\n");

        StringBuilder primaryKeys = new StringBuilder();
        StringBuilder columnComments = new StringBuilder();

        for (int i = 0; i < columns.size(); i++) {
            ColumnInfo column = columns.get(i);

            ddl.append("\t").append(column.getPhysicalName()).append(" ").append(column.getDataType());
            if (column.isNotNull()) {
                ddl.append(" NOT NULL");
            }
            if (i < columns.size() - 1) {
                ddl.append(",");
            }
            ddl.append("\n");

            if (column.isPrimaryKey()) {
                if (primaryKeys.length() > 0) {
                    primaryKeys.append(", ");
                }
                primaryKeys.append(column.getPhysicalName());
            }

            columnComments.append("COMMENT ON COLUMN ").append(schemaName).append(".").append(tableName).append(".")
                    .append(column.getPhysicalName()).append(" IS '").append(column.getLogicalName()).append("';\n");
        }

        if (primaryKeys.length() > 0) {
            ddl.append(",\n\tCONSTRAINT PK_").append(tableName).append(" PRIMARY KEY (").append(primaryKeys).append(")\n");
        }

        ddl.append(");\n\n");
        ddl.append("COMMENT ON TABLE ").append(schemaName).append(".").append(tableName)
                .append(" IS '").append(columns.get(0).getLogicalName()).append("';\n");
        ddl.append(columnComments);

        return ddl.toString();
    }

    public static void writeToFile(String ddl, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(ddl);
            System.out.println("DDL script has been written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example list of columns
        List<ColumnInfo> columns = List.of(
                new ColumnInfo("public", "my_table", "Logical Name 1", "column1", "VARCHAR(50)", true, true),
                new ColumnInfo("public", "my_table", "Logical Name 2", "column2", "INT", false, true),
                new ColumnInfo("public", "my_table", "Logical Name 3", "column3", "DATE", false, false)
        );

        // Generate DDL
        String ddl = generateDDL(columns);

        // Write to file
        String filePath = "output_ddl.sql";
        writeToFile(ddl, filePath);
    }
}
