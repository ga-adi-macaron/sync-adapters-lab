package com.ezequielc.syncadapterslab;

/**
 * Created by student on 12/8/16.
 */

public class Stock {
    private String mStockName;
    private String mStockSymbol;
    private double mStockPrice;
    private boolean mNASDAQ;

    public String getStockName() {
        return mStockName;
    }

    public void setStockName(String stockName) {
        mStockName = stockName;
    }

    public String getStockSymbol() {
        return mStockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        mStockSymbol = stockSymbol;
    }

    public double getStockPrice() {
        return mStockPrice;
    }

    public void setStockPrice(double stockPrice) {
        mStockPrice = stockPrice;
    }

    public boolean isNASDAQ() {
        return mNASDAQ;
    }

    public void setNASDAQ(boolean NASDAQ) {
        mNASDAQ = NASDAQ;
    }
}
