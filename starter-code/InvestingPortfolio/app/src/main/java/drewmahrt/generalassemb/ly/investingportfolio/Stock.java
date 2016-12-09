package drewmahrt.generalassemb.ly.investingportfolio;

/**
 * Created by drewmahrt on 11/22/16.
 */

public class Stock {
    private String name;
    private String symbol;
    private Double lastPrice;
    private Double change;
    private Double changePercent;
    private String timeStamp;
    private Double mSDate;
    private Integer marketCap;
    private Integer volume;
    private Double changeYTD;
    private Double changePercentYTD;
    private Integer high;
    private Double low;
    private Double open;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setlastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getmSDate() {
        return mSDate;
    }

    public void setmSDate(Double mSDate) {
        this.mSDate = mSDate;
    }

    public Integer getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Integer marketCap) {
        this.marketCap = marketCap;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Double getChangeYTD() {
        return changeYTD;
    }

    public void setChangeYTD(Double changeYTD) {
        this.changeYTD = changeYTD;
    }

    public Double getChangePercentYTD() {
        return changePercentYTD;
    }

    public void setChangePercentYTD(Double changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }
}
