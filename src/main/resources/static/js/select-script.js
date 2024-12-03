

// Function to generate SELECT script
function generateSelectScript(tableDetails, columnsData) {
    const { tablePhysicalName, tableLogicalName, dbSchemaName } = tableDetails;

    // Calculate max widths for formatting
    const maxPhysicalNameLength = Math.max(...columnsData.map(col => col.physicalColumnName.length));
    // const maxLogicalPlaceholderLength = Math.max(...columnsData.map(col => `#{${col.logicalColumnName.charAt(0).toLowerCase() + col.logicalColumnName.slice(1)}}`.length));
    
    // Generate SELECT header
    let tableLgcNm = tableLogicalName;
    if (tableLgcNm.charAt(0) === tableLgcNm.charAt(0).toLowerCase()){
        tableLgcNm = tableLgcNm.substring(1);
    }
    let sql = `<select id="retrieve${tableLgcNm}" resultType="MData">\n`;
    sql += `  <![CDATA[\n`;
    sql += `  SELECT /* ${tableLgcNm}Mapper - select${tableLgcNm}*/\n`;

    // Generate column definitions
    let columnDefinitions = columnsData.map((col, index) => {
        const logicalColumn = col.logicalColumnName.charAt(0).toLowerCase() + col.logicalColumnName.slice(1);
        const asClause = ` AS "${logicalColumn}"`;
        const comma = index === columnsData.length - 1 ? "" : ",";
        return `    ${col.physicalColumnName.padEnd(maxPhysicalNameLength)}${asClause}${comma}`;
    }).join("\n");
    sql += columnDefinitions + "\n";

    // Add FROM clause
    sql += `  FROM ${dbSchemaName}.${tablePhysicalName}\n`;

    // Generate WHERE conditions
    sql += generateWhereConditions(columnsData);

    // Close the SQL definition
    sql += `  ]]>\n`;
    sql += `</select>\n`;

    return sql;
}


// Trigger script generation
function handleGenerateSelectScript(hotInstance) {
    const { tableDetails, columnsData } = extractDataFromHandsontable(hotInstance);
    const sqlScript = generateSelectScript(tableDetails, columnsData);
    const fileName = `${tableDetails.tableLogicalName}Mapper_Select.sql`;
    downloadFile(fileName, sqlScript);
}