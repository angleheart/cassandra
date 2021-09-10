package analysis;

import objects.Candle;
import java.util.List;

import static util.NumberUtil.roundDouble;

public class CandleUtil {

    public static void applySimpleMovingAverage(List<Candle> candles, int period) {
        double sum = 0;
        int index = 0;
        do{
            sum+=candles.get(index).getClose();
            index++;
        }while(index+1 < period);

        while(index < candles.size()){
            candles.get(index).setSma(period, roundDouble(sum/period));
            sum-=candles.get(index+1-period).getClose();
            index++;
            if (index < candles.size())
                sum+=candles.get(index).getClose();
        }
    }





}
