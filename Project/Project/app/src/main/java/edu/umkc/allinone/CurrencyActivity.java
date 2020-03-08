package edu.umkc.allinone;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Currency activity class
 *
 * @author Jun Yang
 */

public class CurrencyActivity extends AppCompatActivity {
    // Define buttons and edit text fields
    Button currencyButton;
    Button currencyHomeButton;
    EditText amountCurrencyEditText;
    EditText convertedCurrencyEditText;
    Spinner fromCurrencySpinner;
    Spinner toCurrencySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        // Create two spinner to hold currency type (from/to currency)
        fromCurrencySpinner = (Spinner) findViewById(R.id.fromCurrencySpinner);
        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(fromAdapter);
        toCurrencySpinner = (Spinner) findViewById(R.id.toCurrencySpinner);
        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toCurrencySpinner.setAdapter(toAdapter);

        // Initialize button and edit text
        currencyButton = (Button) findViewById(R.id.convertCurrencyButton);
        currencyHomeButton = (Button) findViewById(R.id.currencyHomeButton);
        amountCurrencyEditText = (EditText) findViewById(R.id.amountCurrencyEditText);
        convertedCurrencyEditText = (EditText) findViewById(R.id.convertedCurrencyEditText);

        // Create a click action for currency convert button
        currencyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Get input from spinners and edit text
                String fromCurrency = String.valueOf(fromCurrencySpinner.getSelectedItem());
                String toCurrency = String.valueOf(toCurrencySpinner.getSelectedItem());
                String amountCurrency = amountCurrencyEditText.getText().toString();
                // Call async task to perform currency convert
                new performConvert().execute(fromCurrency, toCurrency, amountCurrency);
            }
        });

        // Create a click action for weather home
        currencyHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CurrencyActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    class performConvert extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            @SuppressLint("WrongThread")
            // Get arguments that got passed into this async task
            String fromCurrency = params[0];
            String toCurrency = params[1];
            Double amountCurrency = Double.parseDouble(params[2]);
            // Initialized the API object
            CurrencyAPI api = new CurrencyAPI();
            JSONObject fetch = null;
            try {
                // Perform currency convert
                fetch = api.fetch(fromCurrency, toCurrency);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Return the converted currency
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(api.getCurrencyRate(fetch) * amountCurrency).replaceAll("\\.00$", "");
        }
        protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            convertedCurrencyEditText.setText(msg);
        }
    }
}
