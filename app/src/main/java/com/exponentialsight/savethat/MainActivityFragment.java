package com.exponentialsight.savethat;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public SavedCouponsFragment fragmentSaved;

    private ArrayList<CouponEntity> couponList; // list of available coupons
    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;
    private int cards;
    SharedPreferences mPrefs;
    Gson gson;
    private AppDatabase dbb;
    private CouponDao couponObj;

    public MainActivityFragment() {
    }


    public void dataChanged() {
        fragmentSaved.adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.save_button)
    public void swipeRight() {
        /*
        * Call the swipe right action manually
        */
        flingContainer.getTopCardListener().selectRight();
    }

    public void addCoupon(Object c) {
        dbb.couponDao().addCoupon((CouponEntity) c);
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

        fragmentSaved = new SavedCouponsFragment();

        dbb = Room.databaseBuilder(getContext().getApplicationContext(), AppDatabase.class, "couponDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        // TODO add coupons through the database
        couponList = new ArrayList<>();
//        couponList.add("BestBuy");
        CouponEntity cTest = new CouponEntity("100% Discount", "Long John Silvers", "RCG");
        couponList.add(new CouponEntity("50% OFF","Chipotle","XXX"));
        couponList.add(new CouponEntity("BUY ONE GET ONE FREE","Burger King","DXM"));
        couponList.add(new CouponEntity("BUY ONE GET SECOND 50% OFF","Portillos","LSX"));
//        addCoupon(cTest);


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
//                Toast.makeText(getContext(), o.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                // if the object is a coupon, just add that directly to an arraylist and then deal with it in the savedcoupons fragment
                // create a savedcouponsfrag then call it's arraylist and populate it with this? how to do user by user basis?
//                String json = gson.toJson(o);
//                Toast.makeText(getContext(), "Swiped Right", Toast.LENGTH_SHORT).show();
                dbb.couponDao().addCoupon((CouponEntity) o);
//                Log.i("TAG", "this is the house"  + dbb.couponDao().getAll().getCount());
//                dataChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                // ask for more data here
                couponList.add(new CouponEntity("Coupon ".concat(String.valueOf(cards)), "Company", "MMM"));
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

    private class DatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
