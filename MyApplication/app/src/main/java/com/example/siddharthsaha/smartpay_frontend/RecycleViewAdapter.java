package com.example.siddharthsaha.smartpay_frontend;

/**
 * Created by Siddharth Saha on 4/11/2017.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private String[] nameDataSet;
    private String[] dealDataSet;
    private String[] categoryDataSet;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView dealView;
        public TextView addressView;
        public TextView nameView;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.cv);
            nameView = (TextView) v.findViewById(R.id.product_name);
            dealView = (TextView) v.findViewById(R.id.product_deal);
            addressView = (TextView) v.findViewById(R.id.product_address);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleViewAdapter(String[] nameDataSet, String[] dealDataSet, String[] categoryDataSet) {
        this.nameDataSet = nameDataSet;
        this.dealDataSet = dealDataSet;
        this.categoryDataSet = categoryDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_products, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.dealView.setText(dealDataSet[position]);
        holder.addressView.setText("Diamond District, HAL Old Airport Rd, ISRO Colony, Domlur, Bengaluru, Karnataka 56008, India");
        holder.nameView.setText(nameDataSet[position] + " - " + categoryDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dealDataSet.length;
    }
}
