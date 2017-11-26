package com.exponentialsight.savethat;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String TAG = "SwipeActivity";
    private ImageView image; // image for the coupon
    private FloatingActionButton fabSkip; // button to skip
    private FloatingActionButton fabSave; // button to save
    private ArrayList<String> couponList; // list of available coupons
    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;
    private int i;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // butterknife is used to instantiate views instead of calling "findViewById()"
        ButterKnife.inject(this,view);

        fabSkip = view.findViewById(R.id.skip_fab);
        fabSave = view.findViewById(R.id.save_fab);


        // TODO example on how to use flingContainer
        couponList = new ArrayList<>();
        couponList.add("php");
        couponList.add("bestBuy");
        couponList.add("work");

        // create an array adapter
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.coupons_holder, R.id.helloText, couponList);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("List","removed object");
                couponList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                // do something on the left
                // need access to original object
                // to use: cast object to string (String) dataObj
                Toast.makeText(getContext(), "Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(getContext(), "Swiped Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                // ask for more data here
                couponList.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST","notified");
                i++;
            }

            @Override
            public void onScroll(float v) {
                View view =flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(v < 0 ? -v : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(v > 0 ? v : 0);
            }
        });

        // TODO set onclicklistener for fab 

        return view;
    }
}
