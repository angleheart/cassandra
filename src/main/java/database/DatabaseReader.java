package database;

import objects.Candle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseReader {

    public List<String> getAvailableSymbols(String tableName) {
        try(Connection connection = ConnectionPool.getConnection()) {
            List<String> symbols = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery(
                    "SELECT DISTINCT Symbol FROM " + tableName + ";"
            );
            while (resultSet.next())
                symbols.add(resultSet.getString("Symbol"));
            return symbols;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public List<Candle> getCandles(String symbol, String tableName, boolean adjusted) {
        try(Connection connection = ConnectionPool.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM " + tableName + " WHERE Symbol = '" + symbol + "' ORDER BY Date;"
            );

            List<Candle> toReturn = new ArrayList<>();
            if(adjusted){
                while(resultSet.next()){
                    toReturn.add(
                            new Candle(
                                    resultSet.getDouble("AdjustedOpen"),
                                    resultSet.getDouble("AdjustedHigh"),
                                    resultSet.getDouble("AdjustedLow"),
                                    resultSet.getDouble("AdjustedClose"),
                                    resultSet.getDouble("AdjustedVolume"),
                                    new Date(resultSet.getTimestamp("Date").getTime())
                            )
                    );
                }
            }else{
                while(resultSet.next()){
                    toReturn.add(
                            new Candle(
                                    resultSet.getDouble("Open"),
                                    resultSet.getDouble("High"),
                                    resultSet.getDouble("Low"),
                                    resultSet.getDouble("Close"),
                                    resultSet.getDouble("Volume"),
                                    new Date(resultSet.getTimestamp("Date").getTime())
                            )
                    );
                }
            }
            return toReturn;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }


}
