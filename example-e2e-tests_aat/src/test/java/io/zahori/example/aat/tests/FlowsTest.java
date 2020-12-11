/*
 * Panel Sistemas Informaticos, S.L 
 * Center of Excellence in SQA and Testing.
 */

package io.zahori.example.aat.tests;

import es.panel.cest.zahori.framework.BaseTest;
import io.zahori.example.app.data.Static;
import io.zahori.example.app.data.TestCaseData;
import io.zahori.example.app.flows.SearchFlow;

public class FlowsTest extends BaseTest {

    public void test() {

        /* LOAD TEST DATA */
        TestCaseData testData = new TestCaseData(testContext);

        /* LOAD URL */
        testContext.getBrowser().loadPage(testData.url);

        /* FLOW EXECUTION */
        String flow = testData.testFlow;

        switch (flow) {
        case Static.FLOW_SEARCH:
            SearchFlow searchFlow = new SearchFlow(testContext, testData);
            searchFlow.execute();
            break;
        default:
            testContext.failTest("error.selectflow.notfound");
        }
    }
}
