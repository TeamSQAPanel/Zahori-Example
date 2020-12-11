package io.zahori.example.app.pages;

import es.panel.cest.zahori.framework.Locator;
import es.panel.cest.zahori.framework.Page;
import es.panel.cest.zahori.framework.PageElement;
import es.panel.cest.zahori.framework.TestContext;

public class ResultsPO extends Page {

    private static final long serialVersionUID = -3027498072954750755L;

    private PageElement firstResult = new PageElement(this, "First result", Locator.xpath("//li[@data-bm='9']//a"));

    public ResultsPO(TestContext testContext) {
        super(testContext);
    }

    public void visitFirstResult() {
        String linkText = firstResult.getText();
        firstResult.click();

        testContext.logStepPassedWithScreenshot("Visit first result ''{0}''", linkText);
    }

}
