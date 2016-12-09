package net.serkanbal.syncadapterlab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Serkan on 08/12/16.
 */

public class ResultObject {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Symbol")
    @Expose
    private String symbol;
    @SerializedName("LastPrice")
    @Expose
    private Double lastPrice;
    @SerializedName("Change")
    @Expose
    private Double change;
    @SerializedName("ChangePercent")
    @Expose
    private Double changePercent;
    @SerializedName("Timestamp")
    @Expose
    private String timestamp;
    @SerializedName("MSDate")
    @Expose
    private Integer mSDate;
    @SerializedName("MarketCap")
    @Expose
    private Integer marketCap;
    @SerializedName("Volume")
    @Expose
    private Integer volume;
    @SerializedName("ChangeYTD")
    @Expose
    private Double changeYTD;
    @SerializedName("ChangePercentYTD")
    @Expose
    private Double changePercentYTD;
    @SerializedName("High")
    @Expose
    private Integer high;
    @SerializedName("Low")
    @Expose
    private Double low;
    @SerializedName("Open")
    @Expose
    private Double open;

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     *
     * @param symbol
     * The Symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     *
     * @return
     * The lastPrice
     */
    public Double getLastPrice() {
        return lastPrice;
    }

    /**
     *
     * @param lastPrice
     * The LastPrice
     */
    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    /**
     *
     * @return
     * The change
     */
    public Double getChange() {
        return change;
    }

    /**
     *
     * @param change
     * The Change
     */
    public void setChange(Double change) {
        this.change = change;
    }

    /**
     *
     * @return
     * The changePercent
     */
    public Double getChangePercent() {
        return changePercent;
    }

    /**
     *
     * @param changePercent
     * The ChangePercent
     */
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The Timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     * The mSDate
     */
    public Integer getMSDate() {
        return mSDate;
    }

    /**
     *
     * @param mSDate
     * The MSDate
     */
    public void setMSDate(Integer mSDate) {
        this.mSDate = mSDate;
    }

    /**
     *
     * @return
     * The marketCap
     */
    public Integer getMarketCap() {
        return marketCap;
    }

    /**
     *
     * @param marketCap
     * The MarketCap
     */
    public void setMarketCap(Integer marketCap) {
        this.marketCap = marketCap;
    }

    /**
     *
     * @return
     * The volume
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     *
     * @param volume
     * The Volume
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     *
     * @return
     * The changeYTD
     */
    public Double getChangeYTD() {
        return changeYTD;
    }

    /**
     *
     * @param changeYTD
     * The ChangeYTD
     */
    public void setChangeYTD(Double changeYTD) {
        this.changeYTD = changeYTD;
    }

    /**
     *
     * @return
     * The changePercentYTD
     */
    public Double getChangePercentYTD() {
        return changePercentYTD;
    }

    /**
     *
     * @param changePercentYTD
     * The ChangePercentYTD
     */
    public void setChangePercentYTD(Double changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }

    /**
     *
     * @return
     * The high
     */
    public Integer getHigh() {
        return high;
    }

    /**
     *
     * @param high
     * The High
     */
    public void setHigh(Integer high) {
        this.high = high;
    }

    /**
     *
     * @return
     * The low
     */
    public Double getLow() {
        return low;
    }

    /**
     *
     * @param low
     * The Low
     */
    public void setLow(Double low) {
        this.low = low;
    }

    /**
     *
     * @return
     * The open
     */
    public Double getOpen() {
        return open;
    }

    /**
     *
     * @param open
     * The Open
     */
    public void setOpen(Double open) {
        this.open = open;
    }
}

