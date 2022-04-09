package com.dodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dodo.adesso.R;
import com.dodo.model.Country;

import java.util.ArrayList;


public class CountriesListAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final ArrayList<Country> countries;
    /**
     * CountriesListAdapter()
     */
    public CountriesListAdapter(Context context, ArrayList<Country> countries) {

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

        final Country country = countries.get(position);
        ViewHolder holder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.countries_list_item, parent, false);

            holder = new ViewHolder();
            holder.countryName = convertView.findViewById(R.id.country_list_country_name);
            holder.savedButton = convertView.findViewById(R.id.country_list_saved_button);
            convertView.setTag(holder);
        }

        if (country == null)
            return convertView;

        holder = (ViewHolder) convertView.getTag();
        final ViewHolder viewHolder = holder;

        holder.countryName.setText(R.string.error_capitalize);
        if (country.getName() != null)
            holder.countryName.setText(country.getName());

        ViewHolder finalHolder = holder;
        holder.savedButton.setOnClickListener(v -> finalHolder.savedButton.setBackgroundResource(R.drawable.ic_star));

        //convertView.setOnClickListener(v -> );


        return convertView;
    }


    public static class ViewHolder {

        TextView countryName;
        Button savedButton;

    }

}
