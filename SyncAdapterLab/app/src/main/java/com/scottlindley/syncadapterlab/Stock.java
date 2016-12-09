
package com.scottlindley.syncadapterlab;


public class Stock {
    private boolean isNasdaq;
    private String name;
    private String symbol;
    private Double lastPrice;
    private Double change;
    private Double changePercent;
    private String timestamp;
    private Double mSDate;
    private Integer marketCap;
    private Integer volume;
    private Double changeYTD;
    private Double changePercentYTD;
    private Integer high;
    private Double low;
    private Double open;

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    public boolean isNasdaq() {
        return isNasdaq;
    }

    public void setNasdaq(boolean nasdaq) {
        isNasdaq = nasdaq;
    }
    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 
     * @param symbol
     *     The Symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 
     * @return
     *     The lastPrice
     */
    public Double getLastPrice() {
        return lastPrice;
    }

    /**
     * 
     * @param lastPrice
     *     The LastPrice
     */
    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    /**
     * 
     * @return
     *     The change
     */
    public Double getChange() {
        return change;
    }

    /**
     * 
     * @param change
     *     The Change
     */
    public void setChange(Double change) {
        this.change = change;
    }

    /**
     * 
     * @return
     *     The changePercent
     */
    public Double getChangePercent() {
        return changePercent;
    }

    /**
     * 
     * @param changePercent
     *     The ChangePercent
     */
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The Timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The mSDate
     */
    public Double getMSDate() {
        return mSDate;
    }

    /**
     * 
     * @param mSDate
     *     The MSDate
     */
    public void setMSDate(Double mSDate) {
        this.mSDate = mSDate;
    }

    /**
     * 
     * @return
     *     The marketCap
     */
    public Integer getMarketCap() {
        return marketCap;
    }

    /**
     * 
     * @param marketCap
     *     The MarketCap
     */
    public void setMarketCap(Integer marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * 
     * @return
     *     The volume
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * 
     * @param volume
     *     The Volume
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * 
     * @return
     *     The changeYTD
     */
    public Double getChangeYTD() {
        return changeYTD;
    }

    /**
     * 
     * @param changeYTD
     *     The ChangeYTD
     */
    public void setChangeYTD(Double changeYTD) {
        this.changeYTD = changeYTD;
    }

    /**
     * 
     * @return
     *     The changePercentYTD
     */
    public Double getChangePercentYTD() {
        return changePercentYTD;
    }

    /**
     * 
     * @param changePercentYTD
     *     The ChangePercentYTD
     */
    public void setChangePercentYTD(Double changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }

    /**
     * 
     * @return
     *     The high
     */
    public Integer getHigh() {
        return high;
    }

    /**
     * 
     * @param high
     *     The High
     */
    public void setHigh(Integer high) {
        this.high = high;
    }

    /**
     * 
     * @return
     *     The low
     */
    public Double getLow() {
        return low;
    }

    /**
     * 
     * @param low
     *     The Low
     */
    public void setLow(Double low) {
        this.low = low;
    }

    /**
     * 
     * @return
     *     The open
     */
    public Double getOpen() {
        return open;
    }

    /**
     * 
     * @param open
     *     The Open
     */
    public void setOpen(Double open) {
        this.open = open;
    }

}
