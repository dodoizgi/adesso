package com.dodo.database;

import com.dodo.model.Countries;
import com.dodo.model.Country;
import com.dodo.utility.Utility;

import org.json.JSONException;

import java.util.ArrayList;

public class CountriesDatabase {
    private ArrayList<Countries> countriesList = new ArrayList<>();
    private ArrayList<Countries> savedCountriesList = new ArrayList<>();
    private Country country ;

    /**
     * Function: getCountries()
     */
    public ArrayList<Countries> getCountries() {

        return countriesList;
    }

    /**
     * Function: getCountryList()
     */
    public Country getCountry() {

        return country;
    }

    /**
     * Function: getCountries()
     */
    public ArrayList<Countries> getSavedCountries() {

        return savedCountriesList;
    }

    /**
     * Function: addSavedCountries()
     */
    public void addSavedCountries(Countries countries) {

        savedCountriesList.add(countries);
    }

    /**
     * Function: setCountriesStr()
     */
     public void setCountriesStr(String data) throws JSONException {

         this.countriesList.clear();

         if (data == null)
         return;

         countriesList = Utility.getArray(data);
     }

    /**
     * Function: setCountryStr()
     */
    public void setCountryStr(String data) throws JSONException {

        if (data == null)
            return;

        country = Utility.getCountry(data);
    }
}
