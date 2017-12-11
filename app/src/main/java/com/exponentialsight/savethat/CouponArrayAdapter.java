package com.exponentialsight.savethat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Spikes on 12/10/2017.
 */

public class CouponArrayAdapter extends ArrayAdapter<Coupon>{

    ArrayList<Coupon> couponList = new ArrayList<>();

    public CouponArrayAdapter(@NonNull Context context, int resource, int textString, ArrayList<Coupon> cList) {
        super(context, resource, textString, cList);
        couponList = cList;
    }
}
