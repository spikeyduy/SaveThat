package com.exponentialsight.savethat;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spikes on 12/10/2017.
 */

@Dao
public interface CouponDao {
    @Query("SELECT * FROM Coupons")
    List<CouponEntity> getAll();

    @Query("SELECT Deal, _id FROM Coupons")
    Cursor getAllDeals();

    @Query("SELECT Company FROM Coupons")
    List<String> getAllCompany();

    @Query("SELECT Code FROM Coupons")
    List<String> getAllCode();

    @Insert
    public void addCoupon(CouponEntity... couponEntities);

    @Delete
    void delete(CouponEntity couponEntity);

    @Query("SELECT * FROM Coupons")
    public CouponEntity[] getAllCoupons();
}
