package org.apiframework.testdata;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {

    private static final String FILE_PATH = "C:\\Users\\HP\\OneDrive\\Documents\\payloads.xlsx";

    public static Object[][] getTestDataForTestCase(String testCaseName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(FILE_PATH));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        List<Object[]> testData = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header

            String excelTestCaseName = row.getCell(0).getStringCellValue();
            String endpoint = row.getCell(1).getStringCellValue();
            String jsonPayload = row.getCell(2).getStringCellValue();
            String queryParamsJson = row.getCell(3).getStringCellValue();

            // Convert queryParams string to HashMap (if present)
            Map<String, String> queryParams = new HashMap<>();
            if (!queryParamsJson.isEmpty() && !queryParamsJson.equalsIgnoreCase("null")) {
                queryParams = parseQueryParams(queryParamsJson);
            }

            if (excelTestCaseName.equalsIgnoreCase(testCaseName)) {
                testData.add(new Object[]{excelTestCaseName, endpoint, jsonPayload, queryParams});
            }
        }
        workbook.close();
        fis.close();

        return testData.toArray(new Object[0][0]);
    }

    private static Map<String, String> parseQueryParams(String queryParamsJson) {
        Map<String, String> queryParams = new HashMap<>();
        queryParamsJson = queryParamsJson.replace("{", "").replace("}", "").replace("\"", "");
        String[] pairs = queryParamsJson.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                queryParams.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return queryParams;
    }
}