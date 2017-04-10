package com.example.siddharthsaha.smartpay_frontend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Siddharth Saha on 4/10/2017.
 */

public class ProductsTab extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dealertab2products, container, false);
        return rootView;
    }
}
