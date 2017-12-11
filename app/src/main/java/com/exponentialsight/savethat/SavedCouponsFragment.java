package com.exponentialsight.savethat;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SavedCouponsFragment extends Fragment {

    private static final String TAG = "SavedCoupons";
    private ArrayList<Coupon> couponArrayList;
    private SharedPreferences mPrefs;
    private AppDatabase dbb;
    private ListView listView;

    public SavedCouponsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_saved_coupons, container, false);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        // TODO FIX THIS YOU SHOULD NOT BE DOING THIS
        dbb = Room.databaseBuilder(getContext().getApplicationContext(), AppDatabase.class, "couponDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, dbb.couponDao().getAllDeals(), new String[] { "Deal" }, new int[] { android.R.id.text1});
        listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return view;
    }

}
