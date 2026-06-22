package restoapp.dao;

import restoapp.config.Koneksi;
import restoapp.model.MenuModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    public List<MenuModel> getAllMenu() {
        List<MenuModel> list = new ArrayList<>();

        String sql = "SELECT * FROM menu_restoran ORDER BY id_menu DESC";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuModel menu = new MenuModel();
                menu.setIdMenu(rs.getInt("id_menu"));
                menu.setNamaMenu(rs.getString("nama_menu"));
                menu.setKategori(rs.getString("kategori"));
                menu.setHarga(rs.getDouble("harga"));
                menu.setStok(rs.getInt("stok"));

                list.add(menu);
            }

        } catch (Exception e) {
            System.out.println("Gagal mengambil data menu: " + e.getMessage());
        }

        return list;
    }

    public List<MenuModel> searchMenu(String keyword) {
        List<MenuModel> list = new ArrayList<>();

        String sql = "SELECT * FROM menu_restoran "
                + "WHERE nama_menu LIKE ? OR kategori LIKE ? "
                + "ORDER BY id_menu DESC";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuModel menu = new MenuModel();
                menu.setIdMenu(rs.getInt("id_menu"));
                menu.setNamaMenu(rs.getString("nama_menu"));
                menu.setKategori(rs.getString("kategori"));
                menu.setHarga(rs.getDouble("harga"));
                menu.setStok(rs.getInt("stok"));

                list.add(menu);
            }

        } catch (Exception e) {
            System.out.println("Gagal mencari data menu: " + e.getMessage());
        }

        return list;
    }

    public boolean insertMenu(MenuModel menu) {
        String sql = "INSERT INTO menu_restoran (nama_menu, kategori, harga, stok) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, menu.getNamaMenu());
            ps.setString(2, menu.getKategori());
            ps.setDouble(3, menu.getHarga());
            ps.setInt(4, menu.getStok());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
            return false;
        }
    }

    public boolean updateMenu(MenuModel menu) {
        String sql = "UPDATE menu_restoran SET nama_menu = ?, kategori = ?, harga = ?, stok = ? WHERE id_menu = ?";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, menu.getNamaMenu());
            ps.setString(2, menu.getKategori());
            ps.setDouble(3, menu.getHarga());
            ps.setInt(4, menu.getStok());
            ps.setInt(5, menu.getIdMenu());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal mengubah menu: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteMenu(int idMenu) {
        String sql = "DELETE FROM menu_restoran WHERE id_menu = ?";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idMenu);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal menghapus menu: " + e.getMessage());
            return false;
        }
    }
}