package data;

import database.ConnectionPool;
import database.DatabaseReader;
import database.DatabaseWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static data.HttpUtil.getResponseBody;

public class Collector {


    public boolean collectDaily(String symbol) {
        return collect(symbol, "Daily", false);
    }

    public boolean updateDaily(String symbol) {
        boolean toReturn = true;
        DatabaseReader reader = new DatabaseReader();
        List<String> symbols = reader.getAvailableSymbols("Daily");
        if (symbols == null) {
            System.out.println("Failed to retrieve saved symbols");
            return false;
        }
        if (symbol.equals("*")) {
            System.out.println("Performing full daily update...");
            for (String toUpdate : symbols) {
                System.out.println("Updating " + toUpdate + "...");
                if (collect(toUpdate, "Daily", true))
                    System.out.println("Update for " + toUpdate + " complete");
                else {
                    System.out.println("Collection failed for " + toUpdate);
                    toReturn = false;
                }
            }
            return toReturn;
        } else {
            if (!symbols.contains(symbol)) {
                System.out.println("Symbol not found");
                return false;
            }
            return collect(symbol, "Daily", true);
        }
    }


    private boolean collect(String symbol, String period, boolean update) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateTo = sdf.format(new Date());
        DatabaseWriter writer = new DatabaseWriter();

        http:
        while (true) {
            ResponseBody responseBody =
                    getResponseBody(
                            new Request(
                                    symbol,
                                    dateTo
                            )
                    );
            if (responseBody == null) {
                System.out.println("Received null response body");
                return false;
            }
            data[] dataResponse = responseBody.getData();
            if (dataResponse.length == 0) {
                System.out.println("Reached end of available data");
                return true;
            }

            dateTo = sdf.format(dataResponse[dataResponse.length - 1].getDate());

            for (data data : dataResponse) {
                Boolean outcome = writer.insert(data, period);
                if (outcome == null) {
                    System.out.println("A database error caused unknown outcome");
                    return false;
                }
                if (outcome) {
                    System.out.println(
                            "Inserted into " + period + " " +
                                    data.getSymbol() + " " +
                                    sdf.format(data.getDate())
                    );
                } else {
                    System.out.println("Data insertion aborted on overlap");
                    if (update)
                        break http;
                }
            }
        }
        return true;
    }


}