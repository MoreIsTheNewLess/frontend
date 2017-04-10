package com.example.siddharthsaha.smartpay_frontend;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Siddharth Saha on 4/10/2017.
 */

public class DealerInfoTab extends Fragment{
    private TextView usernameT, fullnameT, emailT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dealertab1accinfo, container, false);
        usernameT = (TextView) rootView.findViewById(R.id.tab1DUNDisplay);
        fullnameT = (TextView) rootView.findViewById(R.id.tab1DFNDisplay);
        emailT = (TextView) rootView.findViewById(R.id.tab1DEMDisplay);
        String user=this.getArguments().getString("USERNAME_KEY");
        String email = this.getArguments().getString("EMAIL_KEY");
        String name = this.getArguments().getString("FULLNAME_KEY");
        usernameT.setText(user);
        fullnameT.setText(name);
        emailT.setText(email);
        return rootView;
    }
}
