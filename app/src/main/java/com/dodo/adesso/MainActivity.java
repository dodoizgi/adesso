package com.dodo.adesso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dodo.database.CountriesDatabase;
import com.dodo.model.Country;
import com.dodo.utility.Utility;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountriesDatabase countriesDatabase = new CountriesDatabase();
    }


    /**
     * Function: updateCountriesList
     */
    private void updateCountriesList() {

        ArrayList<Country> countriesList = new ArrayList<>();

    }
}