package com.quantity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(

                ApplicationConfig.getProperty("db.url"),
                ApplicationConfig.getProperty("db.username"),
                ApplicationConfig.getProperty("db.password")
        );
    }
}