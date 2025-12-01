package com.arcade.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.engine.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaHikari {
    public static DataSource getDatasource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:./app;AUTO_SERVER=TRUE");
        return dataSource;
    }

    public static void add(User user) {
        try (Connection connection = getDatasource().getConnection()) {

            String insertStatement = "INSERT INTO APP_DB (name) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(insertStatement);
            statement.setString(1, user.getName());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(User user) {
        try (Connection connection = getDatasource().getConnection()) {

            String updateStatement = "UPDATE APP_DB SET name = ? WHERE name = 'Charger'";
            PreparedStatement statement = connection.prepareStatement(updateStatement);
            statement.setString(1, user.getName());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAll(User user) {
        try (Connection connection = getDatasource().getConnection()) {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM APP_DB");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<UserEntity> findAll(User user) {
        List<UserEntity> userEntityList = new ArrayList<>();

        try (Connection connection = getDatasource().getConnection()) {

            Statement statement = connection.createStatement();
            String selectAllStatement = "SELECT * FROM APP_DB";
            ResultSet rs = statement.executeQuery(selectAllStatement);
            while (rs.next()){
                UserEntity user1 = new UserEntity((long) rs.getInt(1), rs.getString(2));
                userEntityList.add(user1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntityList;
    }
}
