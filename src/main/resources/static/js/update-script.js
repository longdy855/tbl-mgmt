// Function to generate UPDATE script
function generateUpdateScript(tableDetails, columnsData) {
    const { tablePhysicalName, tableLogicalName, dbSchemaName } = tableDetails;

    // Generate UPDATE statement
    let tableLgcNm = tableLogicalName;
    if (tableLgcNm.charAt(0) === tableLgcNm.charAt(0).toLowerCase()){
        tableLgcNm = tableLgcNm.substring(1);
    }
    let sql = `<update id="update${tableLgcNm}">\n`;
    sql += `  <![CDATA[\n`;
    sql += `  UPDATE /* ${tableLgcNm}Mapper - update${tableLgcNm}*/\n`;
    sql += `    ${dbSchemaName}.${tablePhysicalName}\n`;
    sql += `  SET\n`;

    // Generate SET clause
    let setClause = columnsData
        .filter(col => col.pk !== true) // Exclude primary key columns
        .map((col, index) => {
            const logicalColumn = col.logicalColumnName.charAt(0).toLowerCase() + col.logicalColumnName.slice(1);
            const coalesceExpression = `COALESCE(#{${logicalColumn}}, ${col.physicalColumnName})`;
            const comma = index === columnsData.filter(c => c.pk !== true).length - 1 ? "" : ",";
            return `    ${col.physicalColumnName} = ${coalesceExpression}${comma}`;
        }).join("\n");
    sql += setClause + "\n";

    // Generate WHERE conditions
    sql += generateWhereConditions(columnsData);

    // Close the SQL definition
    sql += `  ]]>\n`;
    sql += `</update>\n`;

    return sql;
}

// Trigger UPDATE script generation
function handleGenerateUpdateScript(hotInstance) {
    const { tableDetails, columnsData } = extractDataFromHandsontable(hotInstance);
    const sqlScript = generateUpdateScript(tableDetails, columnsData);
    const fileName = `${tableDetails.tableLogicalName}Mapper_Update.sql`;
    downloadFile(fileName, sqlScript);
}