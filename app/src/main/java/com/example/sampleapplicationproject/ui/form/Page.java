package com.example.sampleapplicationproject.ui.form;

public enum Page {

    ACCOUNT_LIST(1),
    CONTRACTS(2);

    private final int pageID;

    Page(int pageID) {
        this.pageID = pageID;
    }

    public int getPageID() {
        return pageID;
    }
}