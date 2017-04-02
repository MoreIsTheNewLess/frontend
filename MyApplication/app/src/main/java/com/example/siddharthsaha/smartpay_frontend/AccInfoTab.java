package com.example.siddharthsaha.smartpay_frontend;

//import android.app.Fragment;
import android.support.v4.app.  Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Siddharth Saha on 4/2/2017.
 */

public class AccInfoTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1accinfo, container, false);
        return rootView;
    }
}
