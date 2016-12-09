package ly.generalassemb.drewmahrt.stockpriceclient;

/**
 * Created by drewmahrt on 12/5/16.
 */

public class Stock {
    private String name, symbol, price, exchange;

    public Stock(String name, String symbol, String price, String exchange) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.exchange = exchange;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
