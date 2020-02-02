package edu.umkc.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Create a Button object for home
        final Button homeButton = (Button) findViewById(R.id.home_button);

        // Create a click action for home
        homeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
