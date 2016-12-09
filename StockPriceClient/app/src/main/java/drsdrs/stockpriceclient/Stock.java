package drsdrs.stockpriceclient;

/**
 * Created by ds on 12/8/16.
 */

public class Stock {

    private String mExchange;
    private String mStockName;
    private String mStockSymbol;
    private double mStockPrice;

    public Stock(String stockName, double stockPrice, String stockSymbol, String exchange) {
        mStockName = stockName;
        mStockPrice = stockPrice;
        mStockSymbol = stockSymbol;
        mExchange = exchange;

    }

    public String getStockName() {
        return mStockName;
    }

    public void setStockName(String stockName) {
        mStockName = stockName;
    }

    public double getStockPrice() {
        return mStockPrice;
    }

    public void setStockPrice(double stockPrice) {
        mStockPrice = stockPrice;
    }

    public String getStockSymbol() {
        return mStockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        mStockSymbol = stockSymbol;
    }

    public String getExchange() {
        return mExchange;
    }

    public void setExchange(String exchange) {
        mExchange = exchange;
    }


}

