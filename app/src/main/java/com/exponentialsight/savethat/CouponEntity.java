package com.exponentialsight.savethat;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Spikes on 12/10/2017.
 */
@Entity(tableName = "Coupons")
class CouponEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Deal")
    public String deal;

    @ColumnInfo(name = "Company")
    public String companyName;

    @ColumnInfo(name = "code")
    public String code;

    public int getId() {
        return id;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
