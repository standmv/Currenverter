package com.demoyageek.currenverter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by StanDeMoya on 3/17/18.
 */

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    public CurrencyAdapter(@NonNull Context context, @NonNull ArrayList<Currency> currenciesList) {
        super(context, 0, currenciesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.currency_spinner_row, parent, false);
        }

        ImageView imgViewFlag = convertView.findViewById(R.id.flag_image);
        TextView textViewAbbreviation = convertView.findViewById(R.id.currency_abbreviation);

        Currency currencyRow = getItem(position);

        if (currencyRow != null){
            imgViewFlag.setImageResource(currencyRow.getFlagImgId());
            textViewAbbreviation.setText(currencyRow.getAbbreviation());
        }

        return convertView;
    }
}
