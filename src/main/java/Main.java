import config.Configuration;
import console.MainMenuConsole;
import database.ConnectionPool;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String configPath = "cassandra.properties";
        if (args.length > 0)
            configPath = args[0];
        if (init(configPath)){
            System.out.println("Successfully initialized system");
            MainMenuConsole mainConsole = new MainMenuConsole(new Scanner(System.in), "Cassandra > ");
            mainConsole.startConsole();
        }
    }

    private static boolean init(String configPath) {
        if (!Configuration.parse(configPath)) {
            System.out.println("Failed to parse config");
            return false;
        }
        if (!ConnectionPool.init(Configuration.getDataSource())) {
            System.out.println("Failed to establish database connection");
            return false;
        }
        return true;
    }

}

