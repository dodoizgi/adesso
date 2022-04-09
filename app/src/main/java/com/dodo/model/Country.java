package com.dodo.model;

public class Country {

    private String Name;
    private String Code;
    private String CurrencyCodes;
    private String WikiDataId;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCurrencyCodes() {
        return CurrencyCodes;
    }

    public void setCurrencyCodes(String currencyCodes) {
        CurrencyCodes = currencyCodes;
    }

    public String getWikiDataId() {
        return WikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        WikiDataId = wikiDataId;
    }
}
