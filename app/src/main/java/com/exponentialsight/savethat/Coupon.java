package com.exponentialsight.savethat;

/**
 * Coupon class that holds relevant information such as
 * company name, coupon deal, and the image of said company.
 */


public class Coupon {
    private final String deal; // the deal the company is offering
    private final String company; // the company
    private final String code; // the code to apply the coupon

    public Coupon(String deal, String name, String code) {
        this.deal = deal;
        this.company = name;
        this.code = code;
    }

    public String toString() {
        return deal;
    }

    public String getCode() {
        return code;
    }

    public void add(String name, String company, String code) {
        new Coupon(name, company, code);
    }
}
