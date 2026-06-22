package restoapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:mariadb://localhost:3306/db_restoran_231011402280";
                String user = "root";
                String pass = "";

                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
        }

        return conn;
    }
}