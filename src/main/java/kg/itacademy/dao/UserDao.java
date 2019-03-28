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
            User us;
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
        String SQL = "SELECT * FROM users where id = ?";
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
    public User getCategoryByUser() {
        User us = null;
        String SQL = "SELECT * FROM users";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
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
        String SQL = "insert into users (user_name, password, email, date_of_registration) values (?,?,?,NOW())";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            user.setPassword(hidePassword(user));
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean authorize(String login, String password){
        UserDao userDao = new UserDao();
        for(User user : userDao.getAllUsers()){
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
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
