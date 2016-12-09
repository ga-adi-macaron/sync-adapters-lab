package com.colinbradley.syncadapterlab;

/**
 * Created by colinbradley on 12/8/16.
 */

public class StockObject {
    private String mStockName, mStockAbbr, mExchange;
    private double mStockPrice;

    public StockObject(String name, String abbr, double price, String exchange){
        mStockName = name;
        mStockAbbr = abbr;
        mStockPrice = price;
        mExchange = exchange;

    }

    public String getStockName() {
        return mStockName;
    }

    public String getStockAbbr() {
        return mStockAbbr;
    }

    public double getStockPrice() {
        return mStockPrice;
    }

    public String getExchange(){
        return mExchange;
    }
}
