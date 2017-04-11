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
    private String[] mDataset;
    private String[] mDescription;
    private int[] photoid;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView textView;
        public ImageView mImageView;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.cv);
            mImageView = (ImageView) v.findViewById(R.id.person_photo);
            mTextView = (TextView) v.findViewById(R.id.person_name);
            textView = (TextView) v.findViewById(R.id.person_age);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleViewAdapter(String[] myDataset, String[] mDescription, int[] photoid) {
        mDataset = myDataset;
        this.mDescription = mDescription;
        this.photoid = photoid;
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
        holder.mTextView.setText(mDataset[position]);
        holder.textView.setText(mDescription[position]);
        holder.mImageView.setImageResource(photoid[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
