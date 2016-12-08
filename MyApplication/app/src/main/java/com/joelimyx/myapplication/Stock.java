package com.joelimyx.myapplication;

/**
 * Created by Joe on 12/8/16.
 */

public class Stock {
    private String mName, mSymbol, mExchange;
    private double mPrice;

    public Stock(String name, String symbol,String exchange, double price) {
        mName = name;
        mSymbol = symbol;
        mPrice = price;
        mExchange = exchange;
    }

    public String getName() {
        return mName;
    }

    public String getExchange() {
        return mExchange;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public double getPrice() {
        return mPrice;
    }
}
