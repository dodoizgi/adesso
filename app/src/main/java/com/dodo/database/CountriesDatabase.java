package com.dodo.database;

import com.dodo.model.Country;
import com.dodo.utility.Utility;

import java.util.ArrayList;

public class CountriesDatabase {
    private final ArrayList<Country> countryList = new ArrayList<>();

    /**
     * Function: getCountries()
     */
    public ArrayList<Country> getCountries() {

        return countryList;
    }

    /**
     * Function: setCountryStr()
     */
     public void setCountryStr(String data) {

         this.countryList.clear();

         if (data == null)
         return;

         Country[] countries = Utility.getArray(data, Country.class);
         if (countries == null)
         return;

         for (Country country : countries) {
             if (country == null)
                continue;

             addCountry(country);
         }
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
