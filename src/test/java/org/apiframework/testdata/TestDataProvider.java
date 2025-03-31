package org.apiframework.testdata;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestDataProvider {

    @DataProvider(name = "dynamicTestDataProvider")
    public static Object[][] provideTestData(Method method) throws IOException {
        String testCaseName = method.getName(); // Get test case name dynamically
        return ExcelUtils.getTestDataForTestCase(testCaseName);
    }
}