package console;

import database.DatabaseReader;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static console.ConsoleUtil.*;

abstract class Console {

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLACK = "\u001B[30m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\033[0;96m";
    public static final String COLOR_WHITE = "\u001B[37m";

    private final HelpMenu superHelpMenu = new HelpMenu();
    private final String prompt;
    private boolean end;

    final Scanner scanner;

    abstract boolean handle(String input);

    abstract HelpMenu getHelpMenu();

    Console(Scanner scanner, String prompt) {
        this.scanner = scanner;
        this.prompt = prompt;
        superHelpMenu.addOption("LIST DAILY SYMBOLS", "Display list of daily symbols available");
    }

    public void startConsole() {
        do {
            System.out.print(COLOR_YELLOW + prompt + COLOR_RESET);
            String input = scanner.nextLine();
            switch (input) {
                case "end", "END" -> end = true;
                case "clear" -> System.out.print("\033[H\033[2J");
                case "help" -> getHelpMenu().print();
                case "HELP" -> superHelpMenu.print();
                default -> {
                    if(!superHandle(input)){
                        if (!handle(input.trim()) && !input.trim().equals(""))
                            System.out.println("Unknown command. Enter \"help\" for help");
                    }
                }
            }
        } while (!end);
    }

    private boolean superHandle(String input){
        switch(input){

            case "LIST DAILY SYMBOLS" -> {
                DatabaseReader databaseReader = new DatabaseReader();
                List<String> symbols = databaseReader.getAvailableSymbols("Daily");
                if(symbols == null){
                    printError("Failed to collect symbols");
                    return true;
                }
                for(String symbol : symbols)
                    System.out.println(symbol);
                return true;
            }
        }
        return false;
    }


    static void printAlert(String message){
        java.awt.Toolkit.getDefaultToolkit().beep();
        System.out.println(COLOR_RED + message + COLOR_RESET);
    }

    static void printError(String message) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        System.out.println("[" + COLOR_RED + "ERROR" + COLOR_RESET + "]: " + message);
    }

    static void printWarning(String message) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        System.out.println();
        System.out.println("[" + COLOR_RED + "WARNING" + COLOR_RESET + "]: " + message);
        System.out.println();
    }

    static void printConfirmRequest(){
        System.out.print("Enter \"" + COLOR_CYAN + "Confirm" + COLOR_RESET + "\" to validate this action: ");
    }

    static void printSuccess(String message) {
        System.out.println("[" + COLOR_GREEN + "SUCCESS" + COLOR_RESET + "]: " + message);
    }

    static boolean isExitCommand(String s) {
        return s.equalsIgnoreCase("END") ||
                s.equalsIgnoreCase("EXIT") ||
                s.equalsIgnoreCase("QUIT") ||
                s.equalsIgnoreCase("CANCEL");
    }

}

