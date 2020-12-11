/*
 * Panel Sistemas Informaticos, S.L 
 * Center of Excellence in SQA and Testing.
 */

package io.zahori.example.app.data;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import es.panel.cest.zahori.exception.ZahoriException;
import es.panel.cest.zahori.files.excel.Excel;
import es.panel.cest.zahori.framework.TestContext;
import io.zahori.example.app.model.Search;

public class TestCaseData {

    private static final String ENVIRONMENT_PARAM = "entorno";
    private static final String DEFAULT_ENVIRONMENT = "pre";
    public static final String EXCEL_PATH = "src/test/resources/excel/";
    public static final String EXCEL_FILE = "TestCases.xls";
    public static final String EXCEL_SHEET = "TestCases";

    private static final int ROW_WITH_HEADERS = 2;
    private static final int ROW_BEGIN_TEST_CASES = 3;

    public String testCase;
    public String environment;
    public String url;
    public String testFlow;
    public Search search;

    public TestCaseData(TestContext testContext) {
        this.testCase = testContext.testCaseName;

        Excel excel = new Excel(EXCEL_PATH + getEnvironmentParam() + "/" + EXCEL_FILE);
        Map<String, String> testCaseData = getTestCaseDataListFromExcel(excel, EXCEL_SHEET, testCase);

        testContext.logInfo("------ TestCaseData ------");

        environment = testCaseData.get(Static.ENTORNO);
        testContext.logInfo("--- Entorno: " + environment);

        url = getUrl(testContext, environment);
        testContext.logInfo("--- Url: " + url);

        testFlow = testCaseData.get(Static.FLOW);
        testContext.logInfo("--- Flujo: " + testFlow);

        search = new Search(testCaseData);
        testContext.logInfo("--- " + search);

    }

    private Map<String, String> getTestCaseDataListFromExcel(Excel excel, String sheet, String testCaseId) {
        int currentRow = ROW_BEGIN_TEST_CASES;
        while (true) {
            Map<String, String> testCaseData = excel.getRowValuesWithHeaders(sheet, ROW_WITH_HEADERS, currentRow);

            // Test case found
            if (testCaseId.equalsIgnoreCase(testCaseData.get(Static.TEST_CASE))) {
                return testCaseData;
            }

            // Blank row without testCaseId -> stop searching
            if (StringUtils.isBlank(testCaseData.get(Static.TEST_CASE))) {
                throw new ZahoriException(testCaseId, "Test case \"" + testCaseId + "\" not found in exel file ");
            }

            // Continue searching...
            currentRow++;
        }
    }

    public static String getEnvironmentParam() {
        return StringUtils.isBlank(System.getProperty(ENVIRONMENT_PARAM)) ? DEFAULT_ENVIRONMENT : System.getProperty(ENVIRONMENT_PARAM);
    }

    private String getUrl(TestContext testContext, String environment) {
        return StringUtils.isEmpty(environment) ? testContext.getProjectProperty(Static.PROP_ZAHORI_TEST_URL + ".pro")
                : testContext.getProjectProperty(Static.PROP_ZAHORI_TEST_URL + "." + environment);
    }
}
