package com.korbkenny.kennysyncadapterlab;

/**
 * Created by drewmahrt on 11/22/16.
 */

public class Stock {
    private String mStockName;
    private int mStockCount;
    private Double mStockPrice;
    private String mStockExchange;

    public Stock(String stockName, int stockCount, Double stockPrice, String stockExchange) {
        mStockName = stockName;
        mStockCount = stockCount;
        mStockPrice = stockPrice;
        mStockExchange = stockExchange;
    }

    public String getStockName() {
        return mStockName;
    }

    public void setStockName(String stockName) {
        mStockName = stockName;
    }

    public int getStockCount() {
        return mStockCount;
    }

    public void setStockCount(int stockCount) {
        mStockCount = stockCount;
    }

    public Double getStockPrice() {
        return mStockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        mStockPrice = stockPrice;
    }

    public String getStockExchange() {
        return mStockExchange;
    }

    public void setStockExchange(String stockExchange) {
        mStockExchange = stockExchange;
    }
}
