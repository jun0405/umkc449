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

/**
 * Translate activity class
 *
 * @author Jun Yang
 */


public class TranslateActivity extends AppCompatActivity {

    Button translateButton;
    Button translateHomeButton;
    Spinner fromSpinner;
    Spinner toSpinner;
    EditText translateEditText;
    EditText translatedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        // Create two spinner to hold languages (from/to languages)
        fromSpinner = (Spinner) findViewById(R.id.from_spinner);
        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);
        toSpinner = (Spinner) findViewById(R.id.to_spinner);
        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        // Initialize button and edit text
        translateButton = (Button) findViewById(R.id.translateButton);
        translateHomeButton = (Button) findViewById(R.id.translateHomeButton);
        translateEditText = (EditText) findViewById(R.id.translateEditText);
        translatedEditText = (EditText) findViewById(R.id.translatedEditText);

        // Create a click action for weather search button
        translateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Get input from spinners and edit text
                String fromLanguage = String.valueOf(fromSpinner.getSelectedItem());
                String toLanguage = String.valueOf(toSpinner.getSelectedItem());
                String fromTest = translateEditText.getText().toString();
                // Call async task to perform text translation
                new performTranslate().execute(fromLanguage, toLanguage, fromTest);
            }
        });

        // Create a click action for weather home
        translateHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TranslateActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    // Asynchronous task for fetching down weather info via REST API
    class performTranslate extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            @SuppressLint("WrongThread")
            // Get arguments that got passed into this async task
            String fromLanguage = params[0];
            String toLanguage = params[1];
            String fromTest = params[2];
            // Initialized the API object
            TranslateAPI api = new TranslateAPI();
            JSONObject fetch = null;
            try {
                // Perform translation
                fetch = api.fetch(fromTest, fromLanguage, toLanguage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Return the translated text
            return api.getTranslatedText(fetch);
        }
        protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            translatedEditText.setText(msg);
        }
    }
}
