package com.example.siddharthsaha.smartpay_frontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    String uname, pass, url;
    DataBaseAssistant assist = new DataBaseAssistant(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v) {
        if(v.getId() == R.id.Login) {
            EditText a = (EditText) findViewById(R.id.userFill);
            uname = a.getText().toString();
            EditText p = (EditText) findViewById(R.id.passFill);
            pass = p.getText().toString();
            url = new String("http://10.0.0.181:8000/login");
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams rp = new RequestParams();
            rp.add("username", uname);
            rp.add("password", pass);
            client.get(url, rp, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    String password = assist.searchPass(uname);
                    if(pass.equals(password)) {
                        Intent i = new Intent(LoginActivity.this, DealerActivity.class);
                        i.putExtra("Username", uname);
                        startActivity(i);
                    }
                    else {
                        Toast error = Toast.makeText(LoginActivity.this, "Username and password dont match", Toast.LENGTH_SHORT);
                        error.show();
                    }

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Intent i = new Intent(LoginActivity.this, UserActivity.class);
                    i.putExtra("Username", uname);
                    startActivity(i);
                }
            });

//            String password = assist.searchPass(uname);
//            if(pass.equals(password)) {
//
//                String type = assist.getType(uname);
//                if(type.equals("Dealer")) {
//                    Intent i = new Intent(this, DealerActivity.class);
//                    i.putExtra("Username", uname);
//                    startActivity(i);
//                }
//                else {
//                    Intent i = new Intent(this, UserActivity.class);
//                    i.putExtra("Username", uname);
//                    startActivity(i);
//                }
//            }
//            else {
//                Toast error = Toast.makeText(LoginActivity.this, "Username and password dont match", Toast.LENGTH_SHORT);
//                error.show();
//            }

        }
        if(v.getId() == R.id.link_to_register) {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        }
    }
}
