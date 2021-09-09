package console;

import data.Collector;

import java.util.Scanner;

public class CollectorConsole extends Console {

    private final HelpMenu helpMenu;

    CollectorConsole(Scanner scanner, String prompt){
        super(scanner, prompt);
        helpMenu = new HelpMenu();
        helpMenu.addOption("collect <period> <symbol>", "Collect data for new ticker");
        helpMenu.addOption("update  <period> <symbol>", "Update data for existing ticker");
    }

    @Override
    boolean handle(String input) {
        String[] splitInput = input.split(" ");

        switch(splitInput[0]){
            case "collect" -> {
                if(splitInput.length != 3){
                    printError("Invalid command syntax");
                    return true;
                }
                switch(splitInput[1]){
                    case "daily" -> {
                        Collector collector = new Collector();

                        if(collector.collectDaily(splitInput[2]))
                            printSuccess("Data collection completed");
                        else
                            printError("Data collection failed");
                        return true;
                    }
                    default -> {
                        printError("Unknown period");
                        return true;
                    }
                }
            }
            case "update" -> {
                if(splitInput.length != 3){
                    printError("Invalid command syntax");
                    return true;
                }
                switch(splitInput[1]){
                    case "daily" -> {
                        Collector collector = new Collector();
                        if(collector.updateDaily(splitInput[2]))
                            printSuccess("Data update completed");
                        else
                            printError("Data update failed");
                        return true;
                    }
                    default -> {
                        printError("Unknown period");
                        return true;
                    }
                }
            }
        }

        return false;
    }







    @Override
    HelpMenu getHelpMenu() {
        return helpMenu;
    }
}