package com.exponentialsight.savethat;

        import android.arch.persistence.room.Database;
        import android.arch.persistence.room.Room;
        import android.arch.persistence.room.RoomDatabase;
        import android.content.Context;

/**
 * Created by Spikes on 12/10/2017.
 */

@Database(entities = CouponEntity.class, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public static volatile AppDatabase INSTANCE;

    public abstract CouponDao couponDao();

    public static AppDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "CouponDB").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
