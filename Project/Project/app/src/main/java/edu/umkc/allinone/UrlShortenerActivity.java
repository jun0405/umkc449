package edu.umkc.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;

public class UrlShortenerActivity extends AppCompatActivity {
    // Define buttons and edit text fields
    Button urlShortenerButton;
    Button urlShortenerHomeButton;
    EditText urlShortenerEditText;
    EditText urlShortenerNewEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_shortener);

        // Initialized buttons and edit text fields
        urlShortenerButton = (Button) findViewById(R.id.urlShortenerButton);
        urlShortenerHomeButton = (Button) findViewById(R.id.urlShortenerHomeButton);
        urlShortenerEditText = (EditText) findViewById(R.id.urlShortenerEditText);
        urlShortenerNewEditText = (EditText) findViewById(R.id.urlShortenerNewEditText);

        // Create a click action for weather search button
        urlShortenerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new getShortenerUrl().execute();
            }
        });

        // Create a click action for URL shortener home
        urlShortenerHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UrlShortenerActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    // Asynchronous task for fetching down weather info via REST API
    class getShortenerUrl extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {
            @SuppressLint("WrongThread")
            String url = urlShortenerEditText.getText().toString();
            UrlShortenerAPI api = new UrlShortenerAPI();
            String newUrl = null;
            try {
                newUrl = api.fetch(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newUrl;
        }
        protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            urlShortenerNewEditText.setText(msg);
        }
    }
}
