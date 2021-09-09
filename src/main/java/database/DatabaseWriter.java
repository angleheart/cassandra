package database;

import data.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;

public class DatabaseWriter {

    public Boolean insert(data data, String tableName) {
        try(Connection connection = ConnectionPool.getConnection()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            connection.createStatement().execute(
                    "INSERT INTO " + tableName + "(" +
                            "Open, " +
                            "High, " +
                            "Low, " +
                            "Close, " +
                            "Volume, " +
                            "AdjustedOpen, " +
                            "AdjustedHigh, " +
                            "AdjustedLow, " +
                            "AdjustedClose, " +
                            "AdjustedVolume, " +
                            "SplitFactor, " +
                            "Symbol, " +
                            "Exchange, " +
                            "Date" +
                            ") VALUES (" +
                            data.getOpen() + ", " +
                            data.getHigh() + ", " +
                            data.getLow() + ", " +
                            data.getClose() + ", " +
                            data.getVolume() + ", " +
                            data.getAdj_open() + ", " +
                            data.getAdj_high() + ", " +
                            data.getAdj_low() + ", " +
                            data.getAdj_close() + ", " +
                            data.getAdj_volume() + ", " +
                            data.getSplit_factor() + ", " +
                            "'" + data.getSymbol() + "', " +
                            "'" + data.getExchange() + "', " +
                            "'" + sdf.format(data.getDate()) + "'" +
                            ");"
            );
            return true;
        } catch (SQLIntegrityConstraintViolationException integrity) {
            return false;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }


}
