package com.exponentialsight.savethat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


public class SavedCouponsFragment extends Fragment {

    private static final String TAG = "SavedCoupons";
    private ListView mListView;
    SimpleCursorAdapter mAdapter;
    private ArrayList<Coupon> couponArrayList;

    public SavedCouponsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_saved_coupons, container, false);


        return view;
    }

}
