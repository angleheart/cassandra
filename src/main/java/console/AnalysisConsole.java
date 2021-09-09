package console;

import analysis.CandleUtil;
import database.DatabaseReader;
import objects.Candle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnalysisConsole extends Console {

    private final HelpMenu helpMenu;
    private List<Candle> loadedCandles;

    AnalysisConsole(Scanner scanner, String prompt) {
        super(scanner, prompt);
        loadedCandles = new ArrayList<>();
        helpMenu = new HelpMenu();
        helpMenu.addOption("load        <period> <symbol> ?adjusted", "Load candles for analysis");
        helpMenu.addOption("sma-stretch <period>", "Simple moving average stretch analysis");
    }

    @Override
    boolean handle(String input) {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "load" -> {
                if (splitInput.length < 3) {
                    printError("Invalid command syntax");
                    return true;
                }
                boolean adjusted = splitInput.length > 3 && splitInput[3].equals("adjusted");
                DatabaseReader reader = new DatabaseReader();
                String tableName = ConsoleUtil.periodToTableName(splitInput[1]);
                if(tableName == null){
                    printError("Invalid period");
                    return true;
                }
                loadedCandles = reader.getCandles(splitInput[2], tableName, adjusted);
                if(loadedCandles == null){
                    printError("Failed to load candles");
                    return true;
                }
                printSuccess("Loaded " + loadedCandles.size() + " candles for " + tableName + " " + splitInput[2]);
                return true;
            }

            case "sma-stretch" -> {
                if(splitInput.length < 2){
                    printError("Invalid command syntax");
                    return true;
                }
                int period;
                try{
                    period = Integer.parseInt(splitInput[1]);
                }catch(Exception e){
                    printError("Invalid period");
                    return true;
                }
                CandleUtil.applySimpleMovingAverage(loadedCandles, period);


            }
        }

        return false;
    }

    @Override
    HelpMenu getHelpMenu() {
        return helpMenu;
    }
}
