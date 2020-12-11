/*
 * Panel Sistemas Informaticos, S.L 
 * Center of Excellence in SQA and Testing.
 */

package io.zahori.example.app.flows;

import es.panel.cest.zahori.framework.TestContext;
import io.zahori.example.app.data.TestCaseData;
import io.zahori.example.app.pages.ResultsPO;
import io.zahori.example.app.pages.SearchPO;

public class SearchFlow {

    /** The TestCase context. */
    private TestContext testContext;

    /** The TestCase data */
    private TestCaseData testData;

    /**
     * Flow constructor
     * 
     * @param testContext
     * @param testData
     */
    public SearchFlow(TestContext testContext, TestCaseData testData) {
        this.testContext = testContext;
        this.testData = testData;
    }

    public void execute() {
        SearchPO searchPO = new SearchPO(testContext);
        searchPO.search(testData.search.getSearchText());

        testContext.logStepPassedWithScreenshot("Search ''{0}'' OK", testData.search.getSearchText());

        ResultsPO resultsPO = new ResultsPO(testContext);
        resultsPO.visitFirstResult();
    }

}
