package com.dodo.adesso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dodo.adapter.CountriesListAdapter;
import com.dodo.model.Country;
import com.dodo.utility.Utility;

import java.util.ArrayList;
import com.dodo.database.CountriesDatabase;


public class MainActivity extends AppCompatActivity {

    private TextView countriesTextView;
    private ConstraintLayout countriesLayout;
    private LinearLayout mainHomeButton;
    private LinearLayout mainSavedButton;
    private CountriesDatabase countriesDatabase = new CountriesDatabase();
    private ListView countriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utility.setFullscreen(this);
        Utility.KeepActivityOnScreen(this);
        Utility.DisableKeyGuard(this);
        Utility.DisableKeyboard(this);

        createMainActivityLayouts();
        updateCountriesList();
    }

    /**
     * Function: createMainActivityLayouts
     *  create the layout of the main page
     */
    private void createMainActivityLayouts() {

        setContentView(R.layout.activity_main);

        countriesTextView = findViewById(R.id.countries_text);
        countriesLayout = findViewById(R.id.countries_layout);
        mainHomeButton = findViewById(R.id.main_home_button);
        mainSavedButton = findViewById(R.id.main_saved_button);
        countriesListView = findViewById(R.id.countries_list_view);
    }

    /**
     * Function: updateCountriesList
     *  updating the List of Countries
     */
    private void updateCountriesList() {

        ArrayList<Country> countriesList =  countriesDatabase.getCountries();
        CountriesListAdapter countriesListAdapter = new CountriesListAdapter(this, countriesList);
        countriesListView.setAdapter(countriesListAdapter);

    }
}