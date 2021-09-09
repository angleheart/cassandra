package console;

public class ConsoleUtil {

    public static String periodToTableName(String period){
        switch(period){
            case "daily" -> {
                return "Daily";
            }
        }
        return null;
    }


}
