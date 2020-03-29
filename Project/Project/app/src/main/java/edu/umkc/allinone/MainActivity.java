package edu.umkc.allinone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main activity class
 *
 * @author Jun Yang
 */

public class MainActivity extends AppCompatActivity {
    // Define buttons
    Button countryButton;
    Button weatherButton;
    Button translateButton;
    Button currencyButton;
    Button httpCatButton;
    Button urlShortenerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialized country button
        countryButton = (Button) findViewById(R.id.countryButton);
        // Create a click action for country button
        countryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Initialized weather button
        weatherButton = (Button) findViewById(R.id.weatherButton);
        // Create a click action for weather button
        weatherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Initialized translate button
        currencyButton = (Button) findViewById(R.id.translateButton);
        // Create a click action for translate button
        currencyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Initialized translate button
        translateButton = (Button) findViewById(R.id.currencyButton);
        // Create a click action for translate button
        translateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Initialized HTTP cat button
        httpCatButton = (Button) findViewById(R.id.httpCatButton);
        // Create a click action for HTTP cat button
        httpCatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HttpCatActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Initialized URL shortener button
        urlShortenerButton = (Button) findViewById(R.id.urlShortenerButton);
        // Create a click action for URL shortener button
        urlShortenerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UrlShortenerActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
