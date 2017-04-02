package com.example.siddharthsaha.smartpay_frontend;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Siddharth Saha on 4/2/2017.
 */

public class NotifsTab  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3notifs, container, false);

        return rootView;
    }
}
