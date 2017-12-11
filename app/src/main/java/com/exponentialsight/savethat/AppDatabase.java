package com.exponentialsight.savethat;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Spikes on 12/10/2017.
 */

@Database(entities = CouponEntity.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CouponDao couponDao();
}
