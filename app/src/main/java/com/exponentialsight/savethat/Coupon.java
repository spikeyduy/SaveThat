package com.exponentialsight.savethat;

/**
 * Coupon class that holds relevant information such as
 * company name, coupon deal, and the image of said company.
 */


public class Coupon {
    private final String deal; // the deal the company is offering
    private final String company; // the company

    public Coupon(String deal, String name) {
        this.deal = deal;
        this.company = name;
    }
}
