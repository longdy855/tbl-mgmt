// Function to generate Java class content
function generateServiceImpl(tableLogicalName) {
    const mapperObjName = tableLogicalName.charAt(0).toLowerCase() + tableLogicalName.slice(1);
    const classContent = `
@Service
public class ${tableLogicalName}ServiceImpl implements ${tableLogicalName}Service {

    @Autowired
    private ${tableLogicalName}Mapper ${mapperObjName}Mapper;

    @Override
    public MData retrieve${tableLogicalName}(MData param) throws MException {
        return ${mapperObjName}Mapper.retrieve${tableLogicalName}(param);
    }

    @Override
    public int register${tableLogicalName}(MData param) throws MException {
        return ${mapperObjName}Mapper.register${tableLogicalName}(param);
    }

    @Override
    public int update${tableLogicalName}(MData param) throws MException {
        return ${mapperObjName}Mapper.update${tableLogicalName}(param);
    }
}
    `.trim();

    return classContent;
}

// Main function to trigger ServiceImpl generation
function handleGenerateServiceImpl(tableLogicalName) {
    let tableLgcNm = tableLogicalName;
    if (tableLgcNm.charAt(0) === tableLgcNm.charAt(0).toLowerCase()){
        tableLgcNm = tableLgcNm.substring(1);
    }
    const classContent = generateServiceImpl(tableLgcNm);
    const fileName = `${tableLgcNm}ServiceImpl.java`;
    downloadFile(fileName, classContent);
}

