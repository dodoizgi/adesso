package com.dodo.database;

import com.dodo.adesso.MainActivity;
import com.dodo.model.Country;
import com.dodo.utility.Utility;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class CountriesDatabase {
    private ArrayList<Country> countryList = new ArrayList<>();

    /**
     * Function: getCountries()
     */
    public ArrayList<Country> getCountries() {

        return countryList;
    }

    /**
     * Function: setCountryStr()
     */
     public void setCountryStr(String data) throws JSONException {

         this.countryList.clear();

         if (data == null)
         return;

         countryList = Utility.getArray(data);
     }

    /**
     * Function: addCountry()
     */
    public void addCountry(Country country) {

        if (country == null)
            return;

        countryList.add(country);
    }
}
