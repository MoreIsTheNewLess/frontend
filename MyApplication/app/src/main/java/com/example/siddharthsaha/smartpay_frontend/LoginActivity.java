package com.example.siddharthsaha.smartpay_frontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DataBaseAssistant assist = new DataBaseAssistant(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v) {
        if(v.getId() == R.id.Login) {
            EditText a = (EditText) findViewById(R.id.userFill);
            String uname = a.getText().toString();
            EditText p = (EditText) findViewById(R.id.passFill);
            String pass = p.getText().toString();
            String password = assist.searchPass(uname);
            if(pass.equals(password)) {
                Intent i = new Intent(this, UserActivity.class);
                i.putExtra("Username", uname);
                startActivity(i);
            }
            else {
                Toast error = Toast.makeText(LoginActivity.this, "Username and password dont match", Toast.LENGTH_SHORT);
                error.show();
            }

        }
        if(v.getId() == R.id.link_to_register) {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        }
    }
}
