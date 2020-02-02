package edu.umkc.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Button and TextView objects for adding balls
        final Button addBallButton = (Button) findViewById(R.id.ball_button);
        final TextView ballsCount = (TextView) findViewById(R.id.balls_count);
        // Create a Button and TextView objects for adding strike
        final Button addStrikeButton = (Button) findViewById(R.id.strike_button);
        final TextView StrikesCount = (TextView) findViewById(R.id.strikes_count);
        // Create a Button object for resetting
        final Button resetButton = (Button) findViewById(R.id.reset_button);
        // Create a Button object for exiting
        final Button exitButton = (Button) findViewById(R.id.exit_button);
        // Create a Button object for about
        final Button aboutButton = (Button) findViewById(R.id.about_button);

        // Create a click action for adding ball
        addBallButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                ballsCount.setText(Integer.toString(Integer.parseInt(ballsCount.getText().toString()) + 1));
                if (Integer.parseInt(ballsCount.getText().toString()) == 4) {
                    // Create an alert dialog builder
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    // Set title for the alert dialog builder
                    alertDialogBuilder.setTitle("UMKC Popup Notification");
                    // Define action and message for the alert dialog
                    alertDialogBuilder
                            .setMessage("Walk!")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    addBallButton.setEnabled(false);
                                    addStrikeButton.setEnabled(false);
                                    dialog.cancel();
                                }
                            });
                    // Create an alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // Display the alert dialog
                    alertDialog.show();
                }
            }
        });

        // Create a click action for adding strike
        addStrikeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                StrikesCount.setText(Integer.toString(Integer.parseInt(StrikesCount.getText().toString()) + 1));
                if (Integer.parseInt(StrikesCount.getText().toString()) == 3) {
                    // Create an alert dialog builder
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    // Set title for the alert dialog builder
                    alertDialogBuilder.setTitle("UMKC Popup Notification");
                    // Define action and message for the alert dialog
                    alertDialogBuilder
                            .setMessage("Out!")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    addBallButton.setEnabled(false);
                                    addStrikeButton.setEnabled(false);
                                    dialog.cancel();
                                }
                            });
                    // Create an alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // Display the alert dialog
                    alertDialog.show();
                }
            }
        });

        // Create a click action for resetting the scorers
        resetButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                ballsCount.setText(Integer.toString(0));
                StrikesCount.setText(Integer.toString(0));
                if (!addBallButton.isEnabled()){
                    addBallButton.setEnabled(true);
                }
                if (!addStrikeButton.isEnabled()) {
                    addStrikeButton.setEnabled(true);
                }
            }
        });

        // Create a click action for exiting the app
        exitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        // Create a click action for about
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
