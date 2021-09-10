package console;

import analysis.CandleUtil;
import objects.Candle;

import java.text.SimpleDateFormat;
import java.util.List;

import static console.ConsoleUtil.cleanDouble;

class SmaStretchReporter {

    void runReport(List<Candle> loadedCandles, int period){
        CandleUtil.applySimpleMovingAverage(loadedCandles, period);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        double maxStretch = Double.MIN_VALUE;
        String maxStretchDate = "Unknown";
        double minStretch = Double.MAX_VALUE;
        String minStretchDate = "Unknown";
        double thisStretch = 0;
        double stretchSum = 0;
        int totalCandlesInReport = 0;
        for (Candle candle : loadedCandles) {
            if (!candle.hasMovingAverage(period))
                continue;
            thisStretch = candle.getSMAStretch(period);
            if (thisStretch > maxStretch) {
                maxStretch = thisStretch;
                maxStretchDate = sdf.format(candle.getDate());
            }
            if (thisStretch < minStretch) {
                minStretch = thisStretch;
                minStretchDate = sdf.format(candle.getDate());
            }
            stretchSum += thisStretch;
            totalCandlesInReport++;
        }
        int candlesThatShareStretch = 0;
        for (Candle candle : loadedCandles) {
            if (!candle.hasMovingAverage(period))
                continue;
            double currStretch = candle.getSMAStretch(period);
            if (currStretch >= thisStretch)
                candlesThatShareStretch++;
        }
        double percentGreater =
                (double)candlesThatShareStretch/totalCandlesInReport*100;
        double percentLesser =
                ((double)(totalCandlesInReport-candlesThatShareStretch)/totalCandlesInReport)*100;

        System.out.println("---[" + period + " Period SMA-Stretch Report]---");
        System.out.println("Total Candles:  \t" + totalCandlesInReport);
        System.out.println("Mean Stretch:   \t" + cleanDouble(
                stretchSum / totalCandlesInReport * 100, 2) + "%");
        System.out.println();
        System.out.println("Max Stretch:    \t" + cleanDouble(maxStretch * 100, 2) + "%");
        System.out.println("Occurred:       \t" + maxStretchDate);
        System.out.println();
        System.out.println("Min Stretch:    \t" + cleanDouble(minStretch * 100, 2) + "%");
        System.out.println("Occurred:       \t" + minStretchDate);
        System.out.println();
        System.out.println("Current:");
        System.out.println("\tClose:  \t" +
                cleanDouble(loadedCandles.get(loadedCandles.size()-1).getClose(), 2));
        System.out.println("\tSMA:    \t" +
                cleanDouble(loadedCandles.get(loadedCandles.size()-1).getSma(period), 2)
        );
        System.out.println("\tStretch:\t" + cleanDouble(thisStretch * 100, 2) + "%");
        System.out.println();
        System.out.println("Candles Greater:\t" + cleanDouble(
                percentGreater, 2) + "%");
        System.out.println("Candles Lesser :\t" + cleanDouble(
                percentLesser,
                2) + "%"
        );
    }


}
