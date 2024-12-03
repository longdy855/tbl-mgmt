// Function to download the generated SQL as a file
function downloadFile(fileName, content) {
    const blob = new Blob([content], { type: "text/plain" });
    const a = document.createElement("a");
    const url = URL.createObjectURL(blob);
    a.href = url;
    a.download = fileName;
    a.click();
}

// Function to generate WHERE conditions for primary keys
function generateWhereConditions(columnsData) {
    const primaryKeys = columnsData.filter(col => col.pk === true);
    if (primaryKeys.length === 0) return "";

    const conditions = primaryKeys.map((pk, index) => {
        const logicalColumn = pk.logicalColumnName.charAt(0).toLowerCase() + pk.logicalColumnName.slice(1);
        const andClause = index === primaryKeys.length - 1 ? "" : " AND";
        return `    ${pk.physicalColumnName} = #{${logicalColumn}}${andClause}`;
    }).join("\n");

    return `  WHERE\n${conditions}\n`;
}


// Example integration with Handsontable
function extractDataFromHandsontable(hotInstance) {
    const tableDetails = {
        // tablePhysicalName: hotInstance.getDataAtCell(2, 2),
        // tableLogicalName: hotInstance.getDataAtCell(3, 2),
        // dbSchemaName: hotInstance.getDataAtCell(1, 1), // Assuming schema name in another Handsontable
        tablePhysicalName: $('#h3TableName').text(),
        tableLogicalName: $('#h3LogicalName').text(),
        dbSchemaName: 'dibmgr', // 
    };

    const columnsData = [];
    for (let row = 0; row < hotInstance.countRows(); row++) {
        const physicalColumnName = hotInstance.getDataAtCell(row, 0);
        const logicalColumnName = hotInstance.getDataAtCell(row, 1);
        const dataType = hotInstance.getDataAtCell(row, 2);
        const pk = hotInstance.getDataAtCell(row, 3);

        if (physicalColumnName) {
            columnsData.push({ physicalColumnName, logicalColumnName, dataType, pk });
        }
    }

    return { tableDetails, columnsData };
}