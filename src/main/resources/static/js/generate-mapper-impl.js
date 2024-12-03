// Function to generate MapperImpl class content
function generateMapperImpl(tableLogicalName) {
    // Ensure the first character of the tableLogicalName is uppercase
    const prefixTableLogicalName = tableLogicalName.charAt(0);
    if (prefixTableLogicalName === prefixTableLogicalName.toLowerCase()) {
        tableLogicalName = tableLogicalName.slice(1);
    }

    // Convert table logical name to mapper object name
    // const mapperObjName = tableLogicalName.charAt(0).toLowerCase() + tableLogicalName.slice(1);

    // Generate the MapperImpl content
    const mapperImplClassContent = `
@Service
public class ${tableLogicalName}MapperImpl implements ${tableLogicalName}Mapper {

    @Autowired
    private ChannelDBDao channelDBDao;

    @Override
    public MData retrieve${tableLogicalName}(MData param) throws MException {
        return channelDBDao.selectOne("retrieve${tableLogicalName}", param);
    }

    @Override
    public int register${tableLogicalName}(MData param) throws MException {
        return channelDBDao.insert("register${tableLogicalName}", param);
    }

    @Override
    public int update${tableLogicalName}(MData param) throws MException {
        return channelDBDao.update("update${tableLogicalName}", param);
    }
}
    `.trim();

    return mapperImplClassContent;
}



// Function to extract table logical name and generate MapperImpl
function handleGenerateMapperImpl(tableLogicalName) {
    let tableLgcNm = tableLogicalName;
    if (tableLgcNm.charAt(0) === tableLgcNm.charAt(0).toLowerCase()){
        tableLgcNm = tableLgcNm.substring(1);
    }
    const classContent = generateMapperImpl(tableLgcNm);
    const fileName = `${tableLgcNm}MapperImpl.java`;
    downloadFile(fileName, classContent);
}
