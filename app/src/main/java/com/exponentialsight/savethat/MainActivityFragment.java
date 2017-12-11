package com.exponentialsight.savethat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Coupon> couponList; // list of available coupons
    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;
    private int cards;
    SharedPreferences mPrefs;
    Gson gson;

    public MainActivityFragment() {
    }

    @OnClick(R.id.save_button)
    public void swipeRight() {
        /*
        * Call the swipe right action manually
        */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.skip_button)
    public void swipeLeft() {
        flingContainer.getTopCardListener().selectLeft();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // butterknife is used to instantiate views instead of calling "findViewById()"
        ButterKnife.inject(this,view);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        final SharedPreferences.Editor editor = mPrefs.edit();
        gson = new Gson();


        // TODO add coupons through the database
        couponList = new ArrayList<>();
//        couponList.add("BestBuy");
        couponList.add(new Coupon("50% OFF","Chipotle","XXX"));
        couponList.add(new Coupon("BUY ONE GET ONE FREE","Burger King","DXM"));
        couponList.add(new Coupon("BUY ONE GET SECOND 50% OFF","Portillos","LSX"));


        // create an array adapter
        final CouponArrayAdapter arrayAdapter = new CouponArrayAdapter(getContext(), R.layout.coupons_holder, R.id.helloText, couponList);

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
                Toast.makeText(getContext(), o.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                // TODO make the dataObj be saved to saved
                // if the object is a coupon, just add that directly to an arraylist and then deal with it in the savedcoupons fragment
                // create a savedcouponsfrag then call it's arraylist and populate it with this? how to do user by user basis?
//                String json = gson.toJson(o);
                Toast.makeText(getContext(), "Swiped Right", Toast.LENGTH_SHORT).show();
//                editor.putString("Coupon", json);
//                editor.apply();
//                String testJ = mPrefs.getString("Coupon", "");
//                Log.i("SAVING COUP", testJ);
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                // ask for more data here
                couponList.add(new Coupon("Coupon ".concat(String.valueOf(cards)), "Company", "MMM"));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST","notified i: " + cards);
                cards++;
            }

            @Override
            public void onScroll(float v) {
//                View view =flingContainer.getSelectedView();
//                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(v < 0 ? -v : 0);
//                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(v > 0 ? v : 0);
            }
        });

        return view;
    }

}
