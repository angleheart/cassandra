package data;

import java.util.Date;

public class data {

    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final double adj_high;
    private final double adj_low;
    private final double adj_close;
    private final double adj_open;
    private final double adj_volume;
    private final double split_factor;
    private final String symbol;
    private final String exchange;
    private final Date date;

    data(
            double open,
            double high,
            double low,
            double close,
            double volume,
            double adj_high,
            double adj_low,
            double adj_close,
            double adj_open,
            double adj_volume,
            double split_factor,
            String symbol,
            String exchange,
            Date date
    ){
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adj_high = adj_high;
        this.adj_low = adj_low;
        this.adj_close = adj_close;
        this.adj_open = adj_open;
        this.adj_volume = adj_volume;
        this.split_factor = split_factor;
        this.symbol = symbol;
        this.exchange = exchange;
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVolume() {
        return volume;
    }

    public double getAdj_high() {
        return adj_high;
    }

    public double getAdj_low() {
        return adj_low;
    }

    public double getAdj_close() {
        return adj_close;
    }

    public double getAdj_open() {
        return adj_open;
    }

    public double getAdj_volume() {
        return adj_volume;
    }

    public double getSplit_factor() {
        return split_factor;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public Date getDate() {
        return date;
    }

}
