package console;

import java.util.Locale;
import java.util.Scanner;

public class MainMenuConsole extends Console {

    private final HelpMenu helpMenu;

    public MainMenuConsole(Scanner scanner, String prompt) {
        super(scanner, prompt);
        helpMenu = new HelpMenu();
        helpMenu.addOption("collector", "Data collection procedures");
        helpMenu.addOption("analyzer ", "Data analysis tools");
    }

    @Override
    boolean handle(String input) {
        switch (input) {
            case "collector" -> {
                CollectorConsole collectorConsole = new CollectorConsole(scanner, "Cassandra > Collector > ");
                collectorConsole.startConsole();
                return true;
            }
            case "analyzer" -> {
                AnalysisConsole analysisConsole = new AnalysisConsole(scanner, "Cassandra > Analyzer > ");
                analysisConsole.startConsole();
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
