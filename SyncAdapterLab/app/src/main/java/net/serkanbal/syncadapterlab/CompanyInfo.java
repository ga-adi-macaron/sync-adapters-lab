package net.serkanbal.syncadapterlab;

/**
 * Created by Serkan on 08/12/16.
 */

public class CompanyInfo {
    String mCompanyName, mSymbol, mExchange, mCurrentStockPrice;

    public CompanyInfo(String companyName, String currentStockPrice, String exchange, String symbol) {
        mCompanyName = companyName;
        mCurrentStockPrice = currentStockPrice;
        mExchange = exchange;
        mSymbol = symbol;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCurrentStockPrice() {
        return mCurrentStockPrice;
    }

    public void setCurrentStockPrice(String currentStockPrice) {
        mCurrentStockPrice = currentStockPrice;
    }

    public String getExchange() {
        return mExchange;
    }

    public void setExchange(String exchange) {
        mExchange = exchange;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }
}
