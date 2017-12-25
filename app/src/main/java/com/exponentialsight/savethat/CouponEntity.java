package com.exponentialsight.savethat;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Coupons")
class CouponEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _id;

    @ColumnInfo(name = "Deal")
    public String deal;

    @ColumnInfo(name = "Company")
    public String companyName;

    @ColumnInfo(name = "Code")
    public String code;

    @ColumnInfo(name = "Time")
    public int time;

    CouponEntity(String deal, String company, String code, int time) {
        setDeal(deal);
        setCompanyName(company);
        setCode(code);
        setTime(time);
    }

    CouponEntity() {

    }

    public String toString(){
        return deal;
    }
    public int getId() {
        return _id;
    }

    String getDeal() {
        return deal;
    }

    private void setDeal(String deal) {
        this.deal = deal;
    }

    String getCompanyName() {
        return companyName;
    }

    private void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
}
