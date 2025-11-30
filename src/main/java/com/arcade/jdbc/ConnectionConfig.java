package com.arcade.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionConfig {
    private static final String url = "jdbc:h2:./app;AUTO_SERVER=TRUE";
    public static void operate() throws SQLException {
        String createStatement = "CREATE TABLE IF NOT EXIST USER (id IDENTITY PRIMARY KEY , name VARCHAR)";
        Connection connection = DriverManager.getConnection(url); //Driver manager creates a connection to Driver(DB)
        Statement statement = connection.createStatement();
        statement.execute(createStatement);
    }
}
