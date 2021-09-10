package console;

import objects.Candle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static util.NumberUtil.roundDouble;

class ExtendedRallyReporter implements ReportRunnable {

    final List<Candle> candles;
    final int durLimit;
    final double percentToEnd;

    ExtendedRallyReporter(List<Candle> candles, int durLimit, double percentToEnd) {
        this.candles = candles;
        this.durLimit = durLimit;
        this.percentToEnd = percentToEnd;
    }

    private class Rally {
        final String startDate;
        final String endDate;
        public final double percentageRan;
        public final int duration;

        Rally(int from, int to) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            startDate = sdf.format(candles.get(from).getDate());
            endDate = sdf.format(candles.get(to).getDate());
            percentageRan = roundDouble(
                    ((candles.get(to).getHigh() - candles.get(from).getLow()) / candles.get(from).getLow()) * 100,
                    2);
            duration = to - from;
        }

        @Override
        public String toString() {
            return "From " + startDate + " to " + endDate + " this gained " + percentageRan + "% " +
                    "in a rally that lasted " + duration + " candles";
        }
    }

    @Override
    public void runReport() {
        List<Rally> rallies = new ArrayList<>();

        int heldLowIndex = 0;
        int peakHighIndex = 0;
        double heldLow = candles.get(0).getLow();
        double peakHigh = candles.get(0).getHigh();

        for (int i = 1; i < candles.size(); i++) {
            Candle currCandle = candles.get(i);

            //If the rally is over by low being taken out
            if(currCandle.getLow() < heldLow){
                //If it still lasted long enough
                if(peakHighIndex-heldLowIndex >= durLimit)
                    rallies.add(new Rally(heldLowIndex, peakHighIndex));

                //Reset to new candle
                heldLowIndex = i;
                peakHighIndex = i;
                heldLow = currCandle.getLow();
                peakHigh = currCandle.getHigh();
                continue;
            }

            //If the low wasn't taken out
            //Push up the peak high
            if(currCandle.getHigh() > peakHigh){
                peakHigh = currCandle.getHigh();
                peakHighIndex = i;
            }

            //Check if the rally has ended bc of drop
            if(bearsHappy(currCandle.getLow(), peakHigh)){
                // If it lasted long enough
                if(peakHighIndex-heldLowIndex >= durLimit)
                    rallies.add(new Rally(heldLowIndex, peakHighIndex));
                heldLowIndex = i;
                heldLow = currCandle.getLow();
                peakHigh = currCandle.getHigh();
                continue;
            }
            //Check if rally ended because we are out of data
            if(i == candles.size() -1){
                if(peakHighIndex-heldLowIndex >= durLimit)
                    rallies.add(new Rally(heldLowIndex, peakHighIndex));
            }
        }

        rallies.sort(Comparator.comparingInt((Rally z) -> z.duration));

        for(Rally rally : rallies){
            System.out.println(rally);
        }

    }

    private boolean bearsHappy(double currLow, double peakHigh) {
        return ((peakHigh-currLow)/peakHigh)*100 >= percentToEnd;
    }



}
