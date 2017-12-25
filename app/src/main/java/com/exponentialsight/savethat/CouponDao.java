package com.exponentialsight.savethat;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;


@Dao
public interface CouponDao {
    @Query("SELECT Deal, Company, Code, Time, _id FROM Coupons")
    Cursor getAll();

//    @Query("SELECT Deal, _id FROM Coupons")
//    Cursor getAllDeals();
//
//    @Query("SELECT Company, _id FROM Coupons")
//    Cursor getAllCompany();
//
//    @Query("SELECT Code FROM Coupons")
//    List<String> getAllCode();

    @Insert
    void addCoupon(CouponEntity... couponEntities);

    @Delete
    void delete(CouponEntity couponEntity);

//    @Query("SELECT * FROM Coupons")
//    CouponEntity[] getAllCoupons();

}
