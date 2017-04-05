package com.example.siddharthsaha.smartpay_frontend;


import android.annotation.SuppressLint;
import android.support.v4.app.  Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by Siddharth Saha on 4/2/2017.
 */

@SuppressLint("ValidFragment")
public class AccInfoTab extends Fragment {
    private TextView usernameT, fullnameT, emailT;
    private String user, full, mail;
    public AccInfoTab(String username, String fullname, String email) {
        this.user = username;
        this.full = fullname;
        this.mail = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.usertab1accinfo, container, false);
        usernameT = (TextView) rootView.findViewById(R.id.tab1UNDisplay);
        fullnameT = (TextView) rootView.findViewById(R.id.tab1FNDisplay);
        emailT = (TextView) rootView.findViewById(R.id.tab1EMDisplay);
//        String name=this.getArguments().getString("NAME_KEY");

        usernameT.setText(user);
        fullnameT.setText(full);
        emailT.setText(mail);
        return rootView;
    }


}
