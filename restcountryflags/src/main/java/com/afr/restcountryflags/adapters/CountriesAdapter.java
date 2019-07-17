package com.afr.restcountryflags.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afr.restcountryflags.R;
import com.afr.restcountryflags.model.Country;

import java.util.List;


public class CountriesAdapter extends BaseAdapter {

    //Atributo
    private List<Country> countries;
    private LayoutInflater layoutInflater;

    //Constructor
    public CountriesAdapter(List<Country> countries, Context context){
        this.countries = countries;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {


        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Método para dar intrucciones de cómo el Adaptador tiene que pintar una determinada fila
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.row_country, null);

        TextView textViewCountry = (TextView) view.findViewById(R.id.idCountry);
        ImageView imageViewFlag = (ImageView) view.findViewById(R.id.idFlag);

        Country country = countries.get(position);

        textViewCountry.setText(country.getName());

        return view;
    }
}
