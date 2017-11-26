package com.exponentialsight.savethat;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String TAG = "SwipeActivity";
    private ImageView image; // image for the coupon
    private FloatingActionButton fabSkip; // button to skip
    private FloatingActionButton fabSave; // button to save
    private List<Coupon> couponList; // list of available coupons
    private SwipeFlingAdapterView flingContainer; // card items to be swiped through

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        fabSkip = view.findViewById(R.id.skip_fab);
        fabSave = view.findViewById(R.id.save_fab);
        flingContainer = view.findViewById(R.id.frame);

        // TODO example on how to use flingContainer
        ArrayList<String> al = new ArrayList<>();
        al.add("php");
        al.add("bestBuy");
        al.add("mcdicks");

        // create an array adapter


        return view;
    }
}
