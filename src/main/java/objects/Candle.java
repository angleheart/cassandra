package objects;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Candle {

    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final Date date;
    private final Map<Integer, Double> simpleMovingAverages;

    public Candle(
            double open,
            double high,
            double low,
            double close,
            double volume,
            Date date
    ) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.date = date;
        this.simpleMovingAverages = new HashMap<>();
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

    public Date getDate() {
        return date;
    }

    public void setSma(int period, double value){
        simpleMovingAverages.put(period, value);
    }

}
