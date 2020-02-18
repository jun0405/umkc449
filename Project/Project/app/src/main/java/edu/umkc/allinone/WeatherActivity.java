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

/**
 * Weather activity class
 *
 * @author Jun Yang
 */

public class WeatherActivity extends AppCompatActivity {
    // Define buttons and edit text fields
    Button weatherSearchButton;
    Button weatherHomeButton;
    EditText zipCodeEditText;
    EditText weatherSummaryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Initialized buttons and edit text fields
        weatherSearchButton = (Button) findViewById(R.id.weatherSearchButton);
        weatherHomeButton = (Button) findViewById(R.id.weatherHomeButton);
        zipCodeEditText = (EditText) findViewById(R.id.zipCodeEditText);
        weatherSummaryEditText = (EditText) findViewById(R.id.weatherSummaryEditText);

        // Create a click action for weather search button
        weatherSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new getWeatherInfo().execute();
            }
        });

        // Create a click action for weather home
        weatherHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    // Asynchronous task for fetching down weather info via REST API
    class getWeatherInfo extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... arg0) {
            @SuppressLint("WrongThread")
            int zipCode = Integer.parseInt(zipCodeEditText.getText().toString());
            StringBuilder sb = new StringBuilder();
            WeatherAPI api = new WeatherAPI(zipCode);
            try {
                JSONObject jsonObj = api.fetch();
                sb.append("Location:\n");
                sb.append("  - City: " + api.getName(jsonObj) + "\n");
                sb.append("  - Longitude : " + api.getLon(jsonObj) + "\n");
                sb.append("  - Latitude: " + api.getLat(jsonObj) + "\n");
                sb.append("Weather:\n");
                sb.append("  - Summary: " +  api.getSummary(jsonObj) + "\n");
                sb.append("  - Description: " +  api.getDescription(jsonObj) + "\n");
                sb.append("Temperature:\n");
                sb.append("  - Current: " + api.getTemp(jsonObj) + "\n");
                sb.append("  - Min: " + api.getTempMin(jsonObj) + "\n");
                sb.append("  - Max: " + api.getTempMax(jsonObj) + "\n");
                sb.append("  - Feels like: " + api.getFeelsLike(jsonObj) + "\n");
                sb.append("  - Pressure: " + api.getPressure(jsonObj) + "\n");
                sb.append("  - Humidity: " + api.getHumidity(jsonObj) + "\n");
                sb.append("Wind:\n");
                sb.append("  - Speed: " + api.getWindSpeed(jsonObj) + "\n");
                sb.append("  - Direction: " + api.getWindDirection(jsonObj) + "\n");
                sb.append("Others:\n");
                sb.append("  - Sunrise: " + api.getSunrise(jsonObj) + "\n");
                sb.append("  - Sunset: " + api.getSunset(jsonObj) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            weatherSummaryEditText.setText(msg);
        }
    }
}
