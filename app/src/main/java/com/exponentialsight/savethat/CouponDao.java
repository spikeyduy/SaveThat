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

    @Insert
    void insertAll(CouponEntity... couponEntities);

    @Delete
    void delete(CouponEntity couponEntity);

    @Query("SELECT * FROM Coupons")
    Cursor getCursorAll();
}
