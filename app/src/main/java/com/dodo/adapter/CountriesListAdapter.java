package com.dodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dodo.adesso.R;
import com.dodo.database.CountriesDatabase;
import com.dodo.fragment.CountryDetailFragment;
import com.dodo.model.Countries;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class CountriesListAdapter extends BaseAdapter {

    private final AppCompatActivity activity;
    private final LayoutInflater inflater;
    private final ArrayList<Countries> countries;
    private final CountriesDatabase countriesDatabase;
    private boolean clicked = false;

    /**
     * CountriesListAdapter()
     */
    public CountriesListAdapter(AppCompatActivity activity, Context context, ArrayList<Countries> countries, CountriesDatabase countriesDatabase) {

        this.activity = activity;
        this.countriesDatabase = countriesDatabase;
        this.countries = countries;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (countries == null)
            return 0;

        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        if (countries == null || position > countries.size())
            return null;

        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Countries countries = this.countries.get(position);

        ViewHolder holder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.countries_list_item, parent, false);

            holder = new ViewHolder();
            holder.countryName = convertView.findViewById(R.id.country_list_country_name);
            holder.savedButton = convertView.findViewById(R.id.country_list_saved_button);
            convertView.setTag(holder);
        }

        if (countries == null)
            return convertView;

        holder = (ViewHolder) convertView.getTag();
        final ViewHolder viewHolder = holder;

        holder.countryName.setText(R.string.error_capitalize);
        if (countries.getName() != null)
            holder.countryName.setText(countries.getName());


        holder.savedButton.setOnClickListener(v -> {
            if (clicked){
                viewHolder.savedButton.setBackgroundResource(R.drawable.ic_star_gray);
            }else {
                viewHolder.savedButton.setBackgroundResource(R.drawable.ic_star);
                countriesDatabase.addSavedCountries(countries);
            }
            changeClicked();
        });

        convertView.setOnClickListener(v -> getCountry(countries.getCode()));


        return convertView;
    }

    /**
     * ViewHolder()
     */
    public static class ViewHolder {
        TextView countryName;
        ConstraintLayout savedButton;
    }

    private void changeClicked(){
        clicked = !clicked;
    }

    /**
     * Function: getCountry
     *  get Country
     */
    private void getCountry(String code) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://wft-geo-db.p.rapidapi.com/v1/geo/countries/"+code)
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

                    countriesDatabase.setCountryStr(string);
                    CountryDetailFragment countryDetailFragment = new CountryDetailFragment(activity, countriesDatabase.getCountry());
                    countryDetailFragment.show(activity.getSupportFragmentManager());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
