package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS users
                (
                id SERIAL PRIMARY KEY,
                name VARCHAR(128),
                lastName VARCHAR(128),
                age INT
                );
                """;

        try {
            connect.getConnection().createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw e ;
        }
    }

    public void dropUsersTable() throws SQLException {
        String dropQuery = """
        DROP TABLE IF EXISTS users;
        """;

        try {
            PreparedStatement statement = connect.getConnection().prepareStatement(dropQuery);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String saveUserQuery = """
                INSERT INTO USERS(name, lastName, age)
                VALUES
                (?, ?, ?);
                """;
        try {
            PreparedStatement statement = connect.getConnection().prepareStatement(saveUserQuery);

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void removeUserById(long id) throws SQLException {
        String removeQuery = """
                DELETE FROM users
                WHERE id = ?
                """;
        try {
            PreparedStatement statement = connect.getConnection().prepareStatement(removeQuery);
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String getAllQuery = """
                SELECT id,
                name,
                lastName,
                age
                FROM users
                """;
        try {
            PreparedStatement statement = connect.getConnection().prepareStatement(getAllQuery);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(name, lastName, age));
            }
            return users;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void cleanUsersTable() throws SQLException {
        String cleanQuery = """
                DELETE FROM users
                """;
        try {
            PreparedStatement statement = connect.getConnection().prepareStatement(cleanQuery);
            statement.executeQuery();
        } catch (SQLException e) {
            throw e;
        }
    }

    Util connect = new Util();
}
