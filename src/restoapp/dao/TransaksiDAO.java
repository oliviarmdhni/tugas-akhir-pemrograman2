package restoapp.dao;

import restoapp.config.Koneksi;
import restoapp.model.TransaksiModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    public List<TransaksiModel> getAllTransaksi() {
        List<TransaksiModel> list = new ArrayList<>();

        String sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TransaksiModel transaksi = new TransaksiModel();

                transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
                transaksi.setTanggal(rs.getString("tanggal"));
                transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
                transaksi.setIdMenu(rs.getInt("id_menu"));
                transaksi.setNamaMenu(rs.getString("nama_menu"));
                transaksi.setHarga(rs.getDouble("harga"));
                transaksi.setJumlah(rs.getInt("jumlah"));
                transaksi.setTotal(rs.getDouble("total"));
                transaksi.setBayar(rs.getDouble("bayar"));
                transaksi.setKembalian(rs.getDouble("kembalian"));

                list.add(transaksi);
            }

        } catch (Exception e) {
            System.out.println("Gagal mengambil data transaksi: " + e.getMessage());
        }

        return list;
    }

    public boolean insertTransaksi(TransaksiModel transaksi) {
        String sql = "INSERT INTO transaksi "
                + "(nama_pelanggan, id_menu, nama_menu, harga, jumlah, total, bayar, kembalian) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, transaksi.getNamaPelanggan());
            ps.setInt(2, transaksi.getIdMenu());
            ps.setString(3, transaksi.getNamaMenu());
            ps.setDouble(4, transaksi.getHarga());
            ps.setInt(5, transaksi.getJumlah());
            ps.setDouble(6, transaksi.getTotal());
            ps.setDouble(7, transaksi.getBayar());
            ps.setDouble(8, transaksi.getKembalian());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
            return false;
        }
    }

    public boolean kurangiStokMenu(int idMenu, int jumlah) {
        String sql = "UPDATE menu_restoran SET stok = stok - ? WHERE id_menu = ?";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, jumlah);
            ps.setInt(2, idMenu);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal mengurangi stok menu: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteTransaksi(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idTransaksi);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal menghapus transaksi: " + e.getMessage());
            return false;
        }
    }

    public List<TransaksiModel> searchTransaksi(String keyword) {
        List<TransaksiModel> list = new ArrayList<>();

        String sql = "SELECT * FROM transaksi "
                + "WHERE nama_pelanggan LIKE ? OR nama_menu LIKE ? "
                + "ORDER BY id_transaksi DESC";

        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TransaksiModel transaksi = new TransaksiModel();

                transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
                transaksi.setTanggal(rs.getString("tanggal"));
                transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
                transaksi.setIdMenu(rs.getInt("id_menu"));
                transaksi.setNamaMenu(rs.getString("nama_menu"));
                transaksi.setHarga(rs.getDouble("harga"));
                transaksi.setJumlah(rs.getInt("jumlah"));
                transaksi.setTotal(rs.getDouble("total"));
                transaksi.setBayar(rs.getDouble("bayar"));
                transaksi.setKembalian(rs.getDouble("kembalian"));

                list.add(transaksi);
            }

        } catch (Exception e) {
            System.out.println("Gagal mencari transaksi: " + e.getMessage());
        }

        return list;
    }
    
    public TransaksiModel getTransaksiById(int idTransaksi) {
    TransaksiModel transaksi = null;

    String sql = "SELECT * FROM transaksi WHERE id_transaksi = ?";

    try {
        Connection conn = Koneksi.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idTransaksi);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            transaksi = new TransaksiModel();

            transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
            transaksi.setTanggal(rs.getString("tanggal"));
            transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
            transaksi.setIdMenu(rs.getInt("id_menu"));
            transaksi.setNamaMenu(rs.getString("nama_menu"));
            transaksi.setHarga(rs.getDouble("harga"));
            transaksi.setJumlah(rs.getInt("jumlah"));
            transaksi.setTotal(rs.getDouble("total"));
            transaksi.setBayar(rs.getDouble("bayar"));
            transaksi.setKembalian(rs.getDouble("kembalian"));
        }

    } catch (Exception e) {
        System.out.println("Gagal mengambil transaksi: " + e.getMessage());
    }

    return transaksi;
}

public boolean tambahStokMenu(int idMenu, int jumlah) {
    String sql = "UPDATE menu_restoran SET stok = stok + ? WHERE id_menu = ?";

    try {
        Connection conn = Koneksi.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, jumlah);
        ps.setInt(2, idMenu);

        ps.executeUpdate();
        return true;

    } catch (Exception e) {
        System.out.println("Gagal menambah stok menu: " + e.getMessage());
        return false;
    }
}

}