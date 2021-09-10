package console;

import analysis.CandleUtil;
import database.DatabaseReader;
import objects.Candle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static console.ConsoleUtil.cleanDouble;

public class AnalysisConsole extends Console {

    private final HelpMenu helpMenu;
    private List<Candle> loadedCandles;

    AnalysisConsole(Scanner scanner, String prompt) {
        super(scanner, prompt);
        loadedCandles = new ArrayList<>();
        helpMenu = new HelpMenu();
        helpMenu.addOption("load        <period> <symbol> ?adjusted",
                "Load candles for analysis");
        helpMenu.addOption("sma-stretch <period>                   ",
                "Simple moving average stretch analysis");
        helpMenu.addOption("ext-rally   <duration> <percentToEnd>  ",
                "Run extended rally report");
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
                if (tableName == null) {
                    printError("Invalid period");
                    return true;
                }
                loadedCandles = reader.getCandles(splitInput[2], tableName, adjusted);
                if (loadedCandles == null) {
                    printError("Failed to load candles");
                    return true;
                }
                printSuccess("Loaded " + loadedCandles.size() + " candles for " + tableName + " " + splitInput[2]);
                return true;
            }

            case "size" -> {
                System.out.println(loadedCandles.size());
                return true;
            }

            case "sma-stretch" -> {
                if (splitInput.length < 2) {
                    printError("Invalid command syntax");
                    return true;
                }
                int period;
                try {
                    period = Integer.parseInt(splitInput[1]);
                    if (period < 1 || period > loadedCandles.size() - 1) {
                        printError("Invalid period");
                        return true;
                    }
                } catch (Exception e) {
                    printError("Invalid period");
                    return true;
                }
                SmaStretchReporter smaReporter = new SmaStretchReporter();
                smaReporter.runReport(loadedCandles, period);
                return true;
            }

            case "ext-rally" -> {
                double percentToEnd;
                int durationThreshold;
                try {
                    durationThreshold = Integer.parseInt(splitInput[1]);
                    percentToEnd = Double.parseDouble(splitInput[2]);
                } catch (Exception e) {
                    printError("Invalid command syntax");
                    return true;
                }
                ExtendedRallyReporter extendedRallyReporter =
                        new ExtendedRallyReporter(loadedCandles, durationThreshold, percentToEnd);
                extendedRallyReporter.runReport();
                return true;
            }
        }

        return false;
    }

    @Override
    HelpMenu getHelpMenu() {
        return helpMenu;
    }
}
