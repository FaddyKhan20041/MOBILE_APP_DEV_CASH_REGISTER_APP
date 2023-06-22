package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button histButton, restockButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        histButton = findViewById(R.id.history_btn);
        restockButton = findViewById(R.id.restock_btn);
        backButton = findViewById(R.id.backButton);

        histButton.setOnClickListener(this);
        restockButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == histButton) {
            openHistoryActivity();
        } else if (v == restockButton) {
            openRestockActivity();
        } else if (v == backButton) {
            openMainActivity();
        }
    }

    private void openHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void openRestockActivity() {
        Intent intent = new Intent(this, RestockActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
