package com.arcade.jdbc;

import java.sql.*;

public class ConnectionConfig {
    private static final String url = "jdbc:h2:./app;AUTO_SERVER=TRUE";

    public static void operate() throws SQLException {
        Connection connection = DriverManager.getConnection(url); //Driver manager creates a connection to Driver(DB)
        PreparedStatement prepareStatement;

        String createStatement = "CREATE TABLE IF NOT EXISTS APP_DB (id IDENTITY PRIMARY KEY , name VARCHAR)";
        Statement statement = connection.createStatement();
        statement.execute(createStatement);

        String insertStatement = "INSERT INTO APP_DB (name) VALUES(?)";
        prepareStatement = connection.prepareStatement(insertStatement);
        prepareStatement.setString(1, "Shampoo");
        prepareStatement.execute();

        String updateStatement = "UPDATE APP_DB SET name = ? WHERE name = 'Shampoo'";
        prepareStatement = connection.prepareStatement(updateStatement);
        prepareStatement.setString(1, "Asus");
        prepareStatement.executeUpdate();

        String selectAllStatement = "SELECT * FROM APP_DB";
        ResultSet rs = statement.executeQuery(selectAllStatement);
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

    }
}
