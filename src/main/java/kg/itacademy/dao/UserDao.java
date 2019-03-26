package kg.itacademy.dao;

import kg.itacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends DbConnector {

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            User us = null;
            while (rs.next()) {
                us = new User(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                users.add(us);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return users;
    }

    public User getUser(int userId) {
        User us = null;
        String SQL = "SELECT * FROM answers where id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                us = new User(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return us;
    }

    public boolean addUser(User user) {
        String SQL = "insert into users (login, email, password, date_of_registration) values (?,?,?,NOW())";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            user.setPassword(hidePassword(user));
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean authorize(User user) {
        String SQL = "select id from users where login = ?";
        int id = -1;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setString(1, user.getLogin());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getInt("id");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        if (id == -1) {
            return false;
        }
        // check password
        return checkLoginAndPassword(user, id);
    }

    public boolean checkLoginAndPassword(User user, int userId) {
        String SQL = "select count(*) as cnt from users where login = ? and password = ?";
        int count = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        if (count == 0) {
            return false;
        }
        return true;
    }

    public String hidePassword(User user) {
        StringBuilder revPass = new StringBuilder("");
        char[] chars = user.getPassword().toCharArray();
        for (int i = user.getPassword().length(); i > 0; i--) {
            revPass.append(chars[i - 1]);
        }
        revPass.append(chars[0]);
        return revPass.toString();
    }

    public boolean deleteUser(int userId) {
        String SQL = "delete from users where id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)

        ) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
