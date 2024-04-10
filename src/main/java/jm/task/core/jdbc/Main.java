package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Gena", "Bukin", (byte) 34);
        userDao.saveUser("Lena", "Golovach", (byte) 30);
        userDao.saveUser("Roma", "Bukin", (byte) 18);
        userDao.saveUser("Dasha", "Bukina", (byte) 32);
        userDao.removeUserById(1);
        userDao.dropUsersTable();
        userDao.getAllUsers();
        userDao.cleanUsersTable();
    }
}
