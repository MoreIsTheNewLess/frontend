package com.example.siddharthsaha.smartpay_frontend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Siddharth Saha on 4/1/2017.
 */

public class SignUpActivity extends Activity{
//    Spinner dropdown;
    DataBaseAssistant assist = new DataBaseAssistant(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        dropdown = (Spinner)findViewById(R.id.spinner1);
//        String[] items = new String[]{"User", "Dealer"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        dropdown.setAdapter(adapter);
    }

    public void onSignUpClick(View v) {
        if(v.getId() == R.id.btnRegister) {
            EditText fullname = (EditText) findViewById(R.id.reg_fullname);
            EditText email = (EditText) findViewById(R.id.reg_email);
            EditText username = (EditText) findViewById(R.id.reg_username);
            EditText passwd = (EditText) findViewById(R.id.reg_password);
            EditText confirmpasswd = (EditText) findViewById(R.id.reg_confirmpassword);
            EditText vpa = (EditText) findViewById(R.id.reg_vpa);
            EditText address = (EditText) findViewById(R.id.reg_address);

            String fullnamestr = fullname.getText().toString();
            String usernamestr = username.getText().toString();
            String emailstr = email.getText().toString();
            String passwdstr = passwd.getText().toString();
            String confirmpasswdstr = confirmpasswd.getText().toString();
            String vpastr = vpa.getText().toString();
            String addressstr = address.getText().toString();

//            String acctype = dropdown.getSelectedItem().toString();
            if(!confirmpasswdstr.equals(passwdstr)) {
                Toast pass = Toast.makeText(SignUpActivity.this, "Passwords dont match", Toast.LENGTH_SHORT);
                pass.show();
            }
            else {
                Account acc = new Account();
                acc.setName(fullnamestr);
                acc.setEmail(emailstr);
                acc.setUsername(usernamestr);
                acc.setPassword(passwdstr);
                acc.setVPA(vpastr);
                acc.setAddress(addressstr);
//                acc.setType(acctype);
                assist.insertAccount(acc);
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        }
    }

    public void onLoginClick(View v) {
        if(v.getId() == R.id.link_to_login) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

    }
}
