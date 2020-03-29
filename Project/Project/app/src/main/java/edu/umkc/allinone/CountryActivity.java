package edu.umkc.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.simple.JSONObject;

import java.io.IOException;

public class CountryActivity extends AppCompatActivity {
    // Define buttons and edit text fields
    Button countrySearchButton;
    Button countryHomeButton;
    EditText countryCodeEditText;
    EditText countrySummaryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        // Initialized buttons and edit text fields
        countrySearchButton = (Button) findViewById(R.id.countrySearchButton);
        countryHomeButton = (Button) findViewById(R.id.countryHomeButton);
        countryCodeEditText = (EditText) findViewById(R.id.countryCodeEditText);
        countrySummaryEditText = (EditText) findViewById(R.id.countrySummaryEditText);

        // Create a click action for weather search button
        countrySearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new getCountryInfo().execute();
            }
        });

        // Create a click action for weather home
        countryHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CountryActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
    // Asynchronous task for fetching down country info via REST API
    class getCountryInfo extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {
            @SuppressLint("WrongThread")
            String countryCode = countryCodeEditText.getText().toString();
            StringBuilder sb = new StringBuilder();
            CountryAPI api = new CountryAPI();
            try {
                JSONObject jsonObj = api.fetch(countryCode);
                sb.append("Name: " + api.getCountryName(jsonObj) + "\n");
                sb.append("Capital: " + api.getCapital(jsonObj) + "\n");
                sb.append("Region: " + api.getRegion(jsonObj) + "\n");
                sb.append("Subregion: " + api.getSubregion(jsonObj) + "\n");
                sb.append("Demonym: " + api.getDemonym(jsonObj) + "\n");
                sb.append("Area in square kilometre: " + api.getArea(jsonObj) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            countrySummaryEditText.setText(msg);
        }
    }
}
