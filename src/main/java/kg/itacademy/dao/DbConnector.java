package kg.itacademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
//    private final String url = "jdbc:postgresql://138.68.52.248:5432/gr33";
//    private final String user = "gruppa33";
//    private final String password = "5e43qwe53";
    public final String url = "jdbc:postgresql://localhost:5432/";
    public final String user = "postgres";
    public final String password = "0778099606k";

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
