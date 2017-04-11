package com.example.siddharthsaha.smartpay_frontend;



import android.support.v4.app.  Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by Siddharth Saha on 4/2/2017.
 */

public class UserInfoTab extends Fragment {
    private TextView usernameT, fullnameT, emailT;
    private String user, full, mail;
    public UserInfoTab() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.usertab1accinfo, container, false);
        usernameT = (TextView) rootView.findViewById(R.id.tab1UNDisplay);
        fullnameT = (TextView) rootView.findViewById(R.id.tab1FNDisplay);
        emailT = (TextView) rootView.findViewById(R.id.tab1EMDisplay);

        user =this.getArguments().getString("USERNAME_KEY");
        full = this.getArguments().getString("FULLNAME_KEY");
        mail = this.getArguments().getString("EMAIL_KEY");

        usernameT.setText(user);
        fullnameT.setText(full);
        emailT.setText(mail);
        return rootView;
    }


}
