package io.zahori.example.app.model;

import java.util.Map;

import io.zahori.example.app.data.Static;

public class Search {

    private String searchText;

    public Search(Map<String, String> testData) {
        this.searchText = testData.get(Static.SEARCH_TEXT);
    }

    @Override
    public String toString() {
        return "Search [searchText=" + searchText + "]";
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

}
