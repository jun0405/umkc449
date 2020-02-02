package edu.umkc.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Button and TextView objects for adding balls
        Button addBallButton = (Button) findViewById(R.id.ball_button);
        final TextView ballsCount = (TextView) findViewById(R.id.balls_count);
        // Create a click action for adding ball
        addBallButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                ballsCount.setText(Integer.toString(Integer.parseInt(ballsCount.getText().toString()) + 1));
            }
        });

        // Create a Button and TextView objects for adding strike
        Button addStrikeButton = (Button) findViewById(R.id.strike_button);
        final TextView StrikesCount = (TextView) findViewById(R.id.strikes_count);
        // Create a click action for adding strike
        addStrikeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                StrikesCount.setText(Integer.toString(Integer.parseInt(StrikesCount.getText().toString()) + 1));
            }
        });

        // Create a Button object for resetting
        Button resetButton = (Button) findViewById(R.id.reset_button);
        // Create a click action for resetting the scorers
        resetButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                ballsCount.setText(Integer.toString(0));
                StrikesCount.setText(Integer.toString(0));
            }
        });

        // Create a Button object for exiting
        Button exitButton = (Button) findViewById(R.id.exit_button);
        // Create a click action for exiting the app
        exitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
