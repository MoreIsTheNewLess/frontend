package com.example.siddharthsaha.smartpay_frontend;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Siddharth Saha on 4/1/2017.
 */

public class SuccessActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        String username = getIntent().getStringExtra("Username");
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("Great Success " + username);
    }
}
