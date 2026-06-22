package restoapp.dao;

import restoapp.config.Koneksi;
import restoapp.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public UserModel login(String username, String password) {
        UserModel user = null;

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNama(rs.getString("nama"));
            }

        } catch (Exception e) {
            System.out.println("Login gagal: " + e.getMessage());
        }

        return user;
    }
}