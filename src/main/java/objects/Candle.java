package objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static console.ConsoleUtil.cleanDouble;

public class Candle {

    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final Date date;
    private final Map<Integer, Double> simpleMovingAverages = new HashMap<>();

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

    public double getSma(int period){
        return simpleMovingAverages.get(period);
    }

    public double getSMAStretch(int period) {
        double sma = simpleMovingAverages.get(period);
        return Double.parseDouble(
                cleanDouble(
                        (close - sma)/sma,
                        3
                )
        );
    }

    public boolean hasMovingAverage(int period){
        return simpleMovingAverages.containsKey(period);
    }

}
