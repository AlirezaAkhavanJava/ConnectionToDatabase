package com.arcade.jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.Objects;

public class ConnectionConfig {
    private static final String url = "jdbc:h2:./app;AUTO_SERVER=TRUE";


    public static void queryExecutor(String sql, Object... params) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        }
    }


    public static void operate() throws SQLException {
        Connection connection = DriverManager.getConnection(url); //Driver manager creates a connection to Driver(DB)
        PreparedStatement prepareStatement;

        String createStatement = "CREATE TABLE IF NOT EXISTS APP_DB (id IDENTITY PRIMARY KEY , name VARCHAR)";
        Statement statement = connection.createStatement();
        statement.execute(createStatement);

        String insertStatement = "INSERT INTO APP_DB (name) VALUES(?)";
        queryExecutor(insertStatement, "Charger");

        String updateStatement = "UPDATE APP_DB SET name = ? WHERE name = 'Charger'";
        queryExecutor(updateStatement , "KKK");

        String selectAllStatement = "SELECT * FROM APP_DB";
        ResultSet rs = statement.executeQuery(selectAllStatement);
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

    }
}
