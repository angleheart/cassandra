package analysis;

import objects.Candle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CandleUtil {

    public static void applySimpleMovingAverage(List<Candle> candles, int period) {
        BigDecimal sum = new BigDecimal(0);
        sum = sum.setScale(8, RoundingMode.HALF_UP);

        for (int i = 0; i < candles.size(); i++) {
            Candle candle = candles.get(i);
            sum = sum.add(BigDecimal.valueOf(candle.getClose()));
            if (i < period - 1)
                continue;
            BigDecimal smaVal = sum.divide(BigDecimal.valueOf(period), RoundingMode.HALF_UP);
            candles.get(i).setSma(period, smaVal.doubleValue());
            sum = sum.subtract(BigDecimal.valueOf(candles.get((i + 1) - period).getClose()));
        }
    }

}
