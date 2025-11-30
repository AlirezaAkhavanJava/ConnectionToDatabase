package com.arcade;


import com.arcade.jdbc.ConnectionConfig;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            ConnectionConfig.operate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
