package com.dodo.adesso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dodo.adapter.CountriesListAdapter;
import com.dodo.model.Countries;
import com.dodo.utility.Utility;

import java.io.IOException;
import java.util.ArrayList;

import com.dodo.database.CountriesDatabase;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {

    private TextView countriesTextView;
    private ConstraintLayout countriesLayout;
    private LinearLayout mainHomeButton;
    private LinearLayout mainSavedButton;
    private CountriesDatabase countriesDatabase = new CountriesDatabase();
    private ListView countriesListView;
    private boolean homeOrSave= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utility.setFullscreen(this);
        Utility.KeepActivityOnScreen(this);
        Utility.DisableKeyGuard(this);
        Utility.DisableKeyboard(this);

        createMainActivityLayouts();
        getCountries();
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

        mainHomeButton.setOnClickListener(v -> {
            homeOrSave=false;
            updateCountriesList();
        });

        mainSavedButton.setOnClickListener(v -> {
            homeOrSave = true;
            updateCountriesList();
        });
    }

    /**
     * Function: updateCountriesList
     *  updating the List of Countries
     */
    public void updateCountriesList() {
        CountriesListAdapter countriesListAdapter = new CountriesListAdapter(MainActivity.this,this, getHomeOrSavedCountryList(),countriesDatabase);
        countriesListView.setAdapter(countriesListAdapter);
    }

    public ArrayList<Countries> getHomeOrSavedCountryList(){

        ArrayList<Countries> countriesHomeOrSavedList = countriesDatabase.getCountries();
        if (homeOrSave)
            countriesHomeOrSavedList = countriesDatabase.getSavedCountries();

        return  countriesHomeOrSavedList;
    }

    /**
     * Function: getCountries
     *  get Countries
     */
    private void getCountries() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://wft-geo-db.p.rapidapi.com/v1/geo/countries?offset=0&limit=10")
                .get()
                .addHeader("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "07aa18c400msh1a44d4c736a78afp10f6c3jsneba556f22993")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    String string = null;
                    if (responseBody != null)
                        string = responseBody.string();

                    countriesDatabase.setCountriesStr(string);
                    updateCountriesList();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}