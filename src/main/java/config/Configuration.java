package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

    public static String ACCESS_KEY;
    public static String DATABASE_URL;
    public static String DATABASE_SERVER;
    public static String DATABASE_PORT;
    public static String DATABASE_NAME;
    public static String DATABASE_USERNAME;
    public static String DATABASE_PASSWORD;

    public static boolean parse(String path) {
        try {
            File file = new File(path);
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));

            ACCESS_KEY = properties.getProperty("ACCESS_KEY");
            DATABASE_URL = properties.getProperty("DATABASE_URL");
            DATABASE_SERVER = properties.getProperty("DATABASE_SERVER");
            DATABASE_PORT = properties.getProperty("DATABASE_PORT");
            DATABASE_NAME = properties.getProperty("DATABASE_NAME");
            DATABASE_USERNAME = properties.getProperty("DATABASE_USERNAME");
            DATABASE_PASSWORD = properties.getProperty("DATABASE_PASSWORD");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DataSource getDataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(getDatabaseConnectionUrl());
            config.setUsername(DATABASE_USERNAME);
            config.setPassword(DATABASE_PASSWORD);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            HikariDataSource dataSource = new HikariDataSource(config);
            dataSource.getConnection().close();
            return new HikariDataSource(config);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDatabaseConnectionUrl() {
        return DATABASE_URL + DATABASE_SERVER + ":" + DATABASE_PORT + "/" + DATABASE_NAME;
    }

}