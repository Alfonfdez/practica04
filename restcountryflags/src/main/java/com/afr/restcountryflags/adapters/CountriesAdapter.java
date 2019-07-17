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
import com.squareup.picasso.Picasso;

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

        TextView textViewCountryName = (TextView) view.findViewById(R.id.idCountryName);
        TextView textViewCountryAlpha2Code = (TextView) view.findViewById(R.id.idCountryAlpha2Code);
        TextView textViewCountryCapital = (TextView) view.findViewById(R.id.idCountryCapital);
        TextView textViewCountryRegion = (TextView) view.findViewById(R.id.idCountryRegion);
        TextView textViewCountrySubregion = (TextView) view.findViewById(R.id.idCountrySubregion);
        TextView textViewCountryPopulation = (TextView) view.findViewById(R.id.idCountryPopulation);

        ImageView imageViewFlag = (ImageView) view.findViewById(R.id.idFlag);


        Country country = countries.get(position);

        textViewCountryName.setText(country.getName());
        textViewCountryAlpha2Code.setText(country.getAlpha2Code());
        textViewCountryCapital.setText(country.getCapital());
        textViewCountryRegion.setText(country.getRegion());
        textViewCountrySubregion.setText(country.getSubregion());
        textViewCountryPopulation.setText(Integer.toString(country.getPopulation()));

        String imageURL = "https://www.countryflags.io/" + country.getAlpha2Code() + "/flat/64.png";

        Picasso.get().load(imageURL).placeholder(R.drawable.placeholder).into(imageViewFlag);

        return view;
    }
}
