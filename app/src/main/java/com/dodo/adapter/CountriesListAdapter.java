package com.dodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;


public class CountriesListAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    /**
     * CountriesListAdapter()
     */
    public CountriesListAdapter(Context context) {


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

    }

    @Override
    public Object getItem(int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        return convertView;
    }


    public static class ViewHolder {

    }

}
