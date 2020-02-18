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
    Button weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
