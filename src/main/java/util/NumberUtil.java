package util;
import static console.ConsoleUtil.cleanDouble;

public class NumberUtil {

    public static double roundDouble(double d){
        return roundDouble(d, 3);
    }

    public static double roundDouble(double d, int places){
        return Double.parseDouble(cleanDouble(d, places));
    }



}
