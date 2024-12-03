// Function to generate the SQL script
function generateInsertScript(tableDetails, columnsData) {
    const { tablePhysicalName, tableLogicalName, dbSchemaName } = tableDetails;

    // Construct the header of the INSERT statement
    let tableLgcNm = tableLogicalName;
    if (tableLgcNm.charAt(0) === tableLgcNm.charAt(0).toLowerCase()){
        tableLgcNm = tableLgcNm.substring(1);
    }
    let sql = `<insert id="register${tableLgcNm}">\n`;
    sql += `  <![CDATA[\n`;
    sql += `  INSERT /* ${tableLgcNm}Mapper - register${tableLgcNm} */\n`;
    sql += `    INTO ${dbSchemaName}.${tablePhysicalName} (\n`;

    // Add column names
    let columnNames = columnsData.map(col => `      ${col.physicalColumnName}`).join(",\n");
    sql += columnNames + "\n    )\n";

    // Add values
    sql += `    VALUES (\n`;
    let values = columnsData.map(col => {
        const logicalColumn = col.logicalColumnName.charAt(0).toLowerCase() + col.logicalColumnName.slice(1);
        let dataType = "";
        if (col.dataType !== "") {
            dataType = col.dataType.split("(")[0].toLowerCase();
        }
        if (col.pk === "Y") {
            return `      #{${logicalColumn}}`;
        } else if (dataType === "timestamp") {
            return `      CURRENT_TIMESTAMP`;
        } else if (dataType === "varchar" || dataType === "" || dataType === "text") {
            return `      COALESCE(#{${logicalColumn}}, '')`;
        } else if (dataType === "numeric") {
            return `      COALESCE(#{${logicalColumn}}, 0)`;
        } else {
            return "";
        }
    }).join(",\n");
    sql += values + "\n    )\n";
    sql += `  ]]>\n`;
    sql += `</insert>`;

    return sql;
}



// Trigger the process
function handleGenerateScript(hotInstance) {
    const { tableDetails, columnsData } = extractDataFromHandsontable(hotInstance);
    const sqlScript = generateInsertScript(tableDetails, columnsData);
    const fileName = `${tableDetails.tableLogicalName}Mapper.sql`;
    downloadFile(fileName, sqlScript);
}
