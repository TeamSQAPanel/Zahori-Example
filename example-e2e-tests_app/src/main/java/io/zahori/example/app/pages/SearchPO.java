package io.zahori.example.app.pages;

import es.panel.cest.zahori.framework.Locator;
import es.panel.cest.zahori.framework.Page;
import es.panel.cest.zahori.framework.PageElement;
import es.panel.cest.zahori.framework.TestContext;

public class SearchPO extends Page {

    private static final long serialVersionUID = -3027498072954750755L;

    private PageElement acceptCookies = new PageElement(this, "Accept cookies", Locator.id("bnp_btn_accept"));
    private PageElement searchField = new PageElement(this, "Search field", Locator.id("sb_form_q"));
    private PageElement searchButton = new PageElement(this, "Search button", Locator.xpath("//label[@class='search icon tooltip']"));

    public SearchPO(TestContext testContext) {
        super(testContext);
    }

    public void search(String searchText) {
        testContext.logInfo("Searching {0} in google", searchText);

        acceptCookies.click();
        testContext.logStepPassedWithScreenshot("Cookies accepted");

        searchField.write(searchText);
        testContext.logStepPassedWithScreenshot("Write search text ''{0}''", searchText);

        searchButton.click();
    }

}
