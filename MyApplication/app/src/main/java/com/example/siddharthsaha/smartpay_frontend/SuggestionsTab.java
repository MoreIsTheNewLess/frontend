package com.example.siddharthsaha.smartpay_frontend;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Siddharth Saha on 4/2/2017.
 */

public class SuggestionsTab  extends Fragment {

    public SuggestionsTab() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.usertab2suggestions, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        String[] names = new String[]{"Chinmaya", "Kanika", "Rohit", "Siddharth", "Sireesh"};
        String[] description = new String[]{"Genius", "Genius", "Genius", "Loafer", "Genius"};
        int[] photoid = new int[]{R.drawable.chinmaya, R.drawable.kanika, R.drawable.rohit, R.drawable.siddharth, R.drawable.sireesh};
        RecycleViewAdapter adapter = new RecycleViewAdapter(names, description, photoid);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }
}
