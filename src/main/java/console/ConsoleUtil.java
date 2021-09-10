package console;

import java.text.DecimalFormat;

public class ConsoleUtil {

    public static String periodToTableName(String period){
        switch(period){
            case "daily" -> {
                return "Daily";
            }
        }
        return null;
    }

    public static String cleanDouble(double d, int places) {
        DecimalFormat format;
        if (places == 3)
            format = new DecimalFormat("0.000");
        else if (places == 2)
            format = new DecimalFormat("0.00");
        else if (places == 1)
            format = new DecimalFormat("0.0");
        else
            format = new DecimalFormat("0");
        return format.format(d);
    }


}
