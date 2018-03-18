package com.demoyageek.currenverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText quantityInput;
    private Spinner fromSpinner, toSpinner;
    private ArrayList<Currency> mCurrenciesList;
    private CurrencyAdapter mCurrencyAdapter;
    private int auxPosition;
    private Map<String, Double> convertRate;
    private ImageView resultImageView;
    private TextView resultTextView, resultCurrencyDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillCurrenciesList();
        populateConvertRateTable();

        fromSpinner = findViewById(R.id.convert_from_spinner);
        toSpinner = findViewById(R.id.convert_to_spinner);
        quantityInput = findViewById(R.id.currency_input);
        resultImageView = findViewById(R.id.result_flag_image);
        resultTextView = findViewById(R.id.result_textView);
        resultCurrencyDesc = findViewById(R.id.currency_description);

        mCurrencyAdapter = new CurrencyAdapter(this, mCurrenciesList);

        fromSpinner.setAdapter(mCurrencyAdapter);
        toSpinner.setAdapter(mCurrencyAdapter);

        ImageButton changeCurrenciesButton = findViewById(R.id.exchange_button);
        changeCurrenciesButton.setOnClickListener(this);

        Button convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exchange_button:
               exchangeSelectedCurrencies();
               break;
            case R.id.convert_button:
                convert();
                break;
        }
    }

    private void fillCurrenciesList(){
        mCurrenciesList = new ArrayList<>();
        mCurrenciesList.add(new Currency("Peso Dominicano", "DOP", R.drawable.dop_flag));
        mCurrenciesList.add(new Currency("Euro", "EUR", R.drawable.eur_flag));
        mCurrenciesList.add(new Currency("Dolar Estadounidense", "USD", R.drawable.usd_flag));
    }

    private void populateConvertRateTable(){
        convertRate = new HashMap<>();
        convertRate.put("DOPtoUSD", 0.02028);
        convertRate.put("USDtoDOP", 49.30);
        convertRate.put("DOPtoEUR", 0.01649);
        convertRate.put("EURtoDOP", 60.6336);
        convertRate.put("USDtoEUR", 0.8132);
        convertRate.put("EURtoUSD", 1.2297);
        convertRate.put("USDtoUSD", 1.00);
        convertRate.put("DOPtoDOP", 1.00);
        convertRate.put("EURtoEUR", 1.00);
    }

    private void exchangeSelectedCurrencies(){
        auxPosition = fromSpinner.getSelectedItemPosition();
        fromSpinner.setSelection(toSpinner.getSelectedItemPosition());
        toSpinner.setSelection(auxPosition);
    }

    private void convert(){
        String editTextInput = quantityInput.getText().toString();
        if (!editTextInput.matches("")){

            Double result, rate;

            Currency fromCurrencySelected = (Currency) fromSpinner.getItemAtPosition(fromSpinner.getSelectedItemPosition());
            Currency toCurrencySelected = (Currency) toSpinner.getItemAtPosition(toSpinner.getSelectedItemPosition());

            rate = convertRate.get(fromCurrencySelected.getAbbreviation()+"to"+toCurrencySelected.getAbbreviation());
            result = Double.parseDouble(editTextInput) * rate;
            Math.round(result);
            resultImageView.setImageResource(toCurrencySelected.getFlagImgId());
            resultCurrencyDesc.setText("("+toCurrencySelected.getAbbreviation()+") "+ toCurrencySelected.getName());
            resultTextView.setText("$"+result.toString());

        }
        else
            Toast.makeText(this, "No Se Ha Digitado Cantidad", Toast.LENGTH_SHORT).show();
    }
}
