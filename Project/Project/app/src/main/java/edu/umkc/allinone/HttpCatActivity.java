package edu.umkc.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.IOException;

/**
 * HTTP cat activity class
 *
 * @author Jun Yang
 */

public class HttpCatActivity extends AppCompatActivity {
    // Define button, image view, and edit text fields
    Button httpCatButton;
    Button httpCatHomeButton;
    ImageView httpCatImageView;
    EditText httpCatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_cat);

        // Initialized buttons, image view, and edit text fields
        httpCatButton = (Button) findViewById(R.id.httpCatButton);
        httpCatHomeButton = (Button) findViewById(R.id.httpCatHomeButton);
        httpCatImageView = (ImageView) findViewById(R.id.httpCatImageView);
        httpCatEditText = (EditText) findViewById(R.id.httpCatEditText);

        // Create a click action for HTTP cat button
        httpCatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new getHttpCat().execute();
            }
        });

        // Create a click action for HTTP cat home
        httpCatHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HttpCatActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    // Asynchronous task for fetching down country info via REST API
    class getHttpCat extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {
            @SuppressLint("WrongThread")
            int httpCode = Integer.parseInt(httpCatEditText.getText().toString());
            HttpCatAPI api = new HttpCatAPI();
            try {
                return api.fetch(httpCode);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            HttpCatAPI api = new HttpCatAPI();
            httpCatImageView.setImageBitmap(api.stringToBitMap(msg));
        }
    }
}
