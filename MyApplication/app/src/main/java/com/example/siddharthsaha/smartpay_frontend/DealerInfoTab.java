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
    private TextView usernameT, VPAT, addressT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dealertab1accinfo, container, false);
        usernameT = (TextView) rootView.findViewById(R.id.tab1DUNDisplay);
        VPAT = (TextView) rootView.findViewById(R.id.tab1DVPADisplay);
        addressT = (TextView) rootView.findViewById(R.id.tab1DADDisplay);
        String user=this.getArguments().getString("USERNAME_KEY");
        String VPA = this.getArguments().getString("VPA_KEY");
        String address = this.getArguments().getString("ADDRESS_KEY");
        usernameT.setText(user);
        VPAT.setText(VPA);
        addressT.setText(address);
        return rootView;
    }
}
