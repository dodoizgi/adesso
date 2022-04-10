package com.dodo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("currencyCodes")
    @Expose
    private List<String> currencyCodes = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("wikiDataId")
    @Expose
    private String wikiDataId;

    public Country() {
        this.code = null;
        this.currencyCodes = null;
        this.name = null;
        this.wikiDataId = null;
    }

    public Country(Country country) {
        this.code = country.code;
        this.currencyCodes = country.currencyCodes;
        this.name = country.name;
        this.wikiDataId = country.wikiDataId;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Country withCode(String code) {
        this.code = code;
        return this;
    }

    public List<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public void setCurrencyCodes(List<String> currencyCodes) {
        this.currencyCodes = currencyCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country withName(String name) {
        this.name = name;
        return this;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

    public Country withWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
        return this;
    }
}
