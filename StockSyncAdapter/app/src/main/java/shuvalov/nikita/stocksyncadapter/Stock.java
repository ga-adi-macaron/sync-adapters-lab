package shuvalov.nikita.stocksyncadapter;

/**
 * Created by NikitaShuvalov on 12/8/16.
 */

public class Stock {
    private long mId;
    private String mStockName;
    private int mStockCount;
    private float mStockPrice;
    private String mExchange;

    public Stock(String stockName, int stockCount, long id, String exchange, float price) {
        mId = id;
        mStockName = stockName;
        mStockCount = stockCount;
        mExchange = exchange;
        mStockPrice = price;
    }

    public String getStockName() {
        return mStockName;
    }

    public float getStockPrice() {
        return mStockPrice;
    }

    public void setStockPrice(float stockPrice) {
        mStockPrice = stockPrice;
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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getExchange() {
        return mExchange;
    }

    public void setExchange(String exchange) {
        mExchange = exchange;
    }
}

