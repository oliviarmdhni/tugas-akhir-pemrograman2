package restoapp.controller;

import restoapp.dao.MenuDAO;
import restoapp.dao.TransaksiDAO;
import restoapp.model.MenuModel;
import restoapp.model.TransaksiModel;
import restoapp.view.TransaksiInternalFrame;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class TransaksiController {

    private TransaksiInternalFrame view;
    private MenuDAO menuDAO;
    private TransaksiDAO transaksiDAO;
    private List<MenuModel> listMenu;

    public TransaksiController(TransaksiInternalFrame view) {
        this.view = view;
        this.menuDAO = new MenuDAO();
        this.transaksiDAO = new TransaksiDAO();

        loadMenu();
        tampilData();

        this.view.getCmbMenu().addActionListener(e -> isiHargaDariMenu());
        this.view.getBtnHitung().addActionListener(e -> hitungTotal());
        this.view.getBtnSimpan().addActionListener(e -> simpanTransaksi());
        this.view.getBtnHapus().addActionListener(e -> hapusTransaksi());
        this.view.getBtnBersih().addActionListener(e -> bersih());

        this.view.getTableTransaksi().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isiFormDariTable();
            }
        });

        this.view.getTxtCari().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cariData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cariData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                cariData();
            }
        });
    }

    private void loadMenu() {
        view.getCmbMenu().removeAllItems();

        listMenu = menuDAO.getAllMenu();

        for (MenuModel menu : listMenu) {
            view.getCmbMenu().addItem(menu.getNamaMenu() + " - Stok: " + menu.getStok());
        }

        isiHargaDariMenu();
    }

    private void isiHargaDariMenu() {
        int index = view.getCmbMenu().getSelectedIndex();

        if (index >= 0 && listMenu != null && index < listMenu.size()) {
            MenuModel menu = listMenu.get(index);
            view.getTxtHarga().setText(String.valueOf(menu.getHarga()));
        }
    }

    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Pelanggan");
        model.addColumn("Menu");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");

        for (TransaksiModel transaksi : transaksiDAO.getAllTransaksi()) {
            model.addRow(new Object[]{
                transaksi.getIdTransaksi(),
                transaksi.getTanggal(),
                transaksi.getNamaPelanggan(),
                transaksi.getNamaMenu(),
                transaksi.getHarga(),
                transaksi.getJumlah(),
                transaksi.getTotal(),
                transaksi.getBayar(),
                transaksi.getKembalian()
            });
        }

        view.getTableTransaksi().setModel(model);
        aturLebarKolom();
    }

    private void cariData() {
        String keyword = view.getTxtCari().getText().trim();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Pelanggan");
        model.addColumn("Menu");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");

        List<TransaksiModel> listTransaksi;

        if (keyword.isEmpty()) {
            listTransaksi = transaksiDAO.getAllTransaksi();
        } else {
            listTransaksi = transaksiDAO.searchTransaksi(keyword);
        }

        for (TransaksiModel transaksi : listTransaksi) {
            model.addRow(new Object[]{
                transaksi.getIdTransaksi(),
                transaksi.getTanggal(),
                transaksi.getNamaPelanggan(),
                transaksi.getNamaMenu(),
                transaksi.getHarga(),
                transaksi.getJumlah(),
                transaksi.getTotal(),
                transaksi.getBayar(),
                transaksi.getKembalian()
            });
        }

        view.getTableTransaksi().setModel(model);
        aturLebarKolom();
    }
    
    private void aturLebarKolom() {
        view.getTableTransaksi().getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        view.getTableTransaksi().getColumnModel().getColumn(1).setPreferredWidth(140);  // Tanggal
        view.getTableTransaksi().getColumnModel().getColumn(2).setPreferredWidth(130);  // Pelanggan
        view.getTableTransaksi().getColumnModel().getColumn(3).setPreferredWidth(150);  // Menu
        view.getTableTransaksi().getColumnModel().getColumn(4).setPreferredWidth(90);   // Harga
        view.getTableTransaksi().getColumnModel().getColumn(5).setPreferredWidth(70);   // Jumlah
        view.getTableTransaksi().getColumnModel().getColumn(6).setPreferredWidth(100);  // Total
        view.getTableTransaksi().getColumnModel().getColumn(7).setPreferredWidth(100);  // Bayar
        view.getTableTransaksi().getColumnModel().getColumn(8).setPreferredWidth(100);  // Kembalian
}

    private void hitungTotal() {
        try {
            String hargaText = view.getTxtHarga().getText().trim();
            String jumlahText = view.getTxtJumlah().getText().trim();
            String bayarText = view.getTxtBayar().getText().trim();

            if (hargaText.isEmpty() || jumlahText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Harga dan jumlah wajib diisi!");
                return;
            }

            double harga = Double.parseDouble(hargaText);
            int jumlah = Integer.parseInt(jumlahText);
            double total = harga * jumlah;

            view.getTxtTotal().setText(String.valueOf(total));

            if (!bayarText.isEmpty()) {
                double bayar = Double.parseDouble(bayarText);
                double kembalian = bayar - total;
                view.getTxtKembalian().setText(String.valueOf(kembalian));
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Jumlah dan bayar harus berupa angka!");
        }
    }

    private void simpanTransaksi() {
        try {
            int index = view.getCmbMenu().getSelectedIndex();

            if (index < 0 || listMenu == null || listMenu.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih menu terlebih dahulu!");
                return;
            }

            String namaPelanggan = view.getTxtNamaPelanggan().getText().trim();
            String jumlahText = view.getTxtJumlah().getText().trim();
            String bayarText = view.getTxtBayar().getText().trim();

            if (namaPelanggan.isEmpty() || jumlahText.isEmpty() || bayarText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama pelanggan, jumlah, dan bayar wajib diisi!");
                return;
            }

            MenuModel menu = listMenu.get(index);

            int jumlah = Integer.parseInt(jumlahText);
            double harga = menu.getHarga();
            double total = harga * jumlah;
            double bayar = Double.parseDouble(bayarText);
            double kembalian = bayar - total;

            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(view, "Jumlah harus lebih dari 0!");
                return;
            }

            if (jumlah > menu.getStok()) {
                JOptionPane.showMessageDialog(view, "Stok menu tidak cukup!");
                return;
            }

            if (bayar < total) {
                JOptionPane.showMessageDialog(view, "Uang bayar kurang!");
                return;
            }

            TransaksiModel transaksi = new TransaksiModel();
            transaksi.setNamaPelanggan(namaPelanggan);
            transaksi.setIdMenu(menu.getIdMenu());
            transaksi.setNamaMenu(menu.getNamaMenu());
            transaksi.setHarga(harga);
            transaksi.setJumlah(jumlah);
            transaksi.setTotal(total);
            transaksi.setBayar(bayar);
            transaksi.setKembalian(kembalian);

            if (transaksiDAO.insertTransaksi(transaksi)) {
                transaksiDAO.kurangiStokMenu(menu.getIdMenu(), jumlah);

                JOptionPane.showMessageDialog(view, "Transaksi berhasil disimpan.");

                bersih();
                loadMenu();
                tampilData();
            } else {
                JOptionPane.showMessageDialog(view, "Transaksi gagal disimpan.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Jumlah dan bayar harus berupa angka!");
        }
    }

    private void hapusTransaksi() {
    if (view.getTxtIdTransaksi().getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Pilih transaksi yang ingin dihapus!");
        return;
    }

    int konfirmasi = JOptionPane.showConfirmDialog(
            view,
            "Yakin ingin menghapus transaksi ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
    );

    if (konfirmasi == JOptionPane.YES_OPTION) {
        int idTransaksi = Integer.parseInt(view.getTxtIdTransaksi().getText().trim());

        TransaksiModel transaksi = transaksiDAO.getTransaksiById(idTransaksi);

        if (transaksi == null) {
            JOptionPane.showMessageDialog(view, "Data transaksi tidak ditemukan!");
            return;
        }

        if (transaksiDAO.deleteTransaksi(idTransaksi)) {
            transaksiDAO.tambahStokMenu(transaksi.getIdMenu(), transaksi.getJumlah());

            JOptionPane.showMessageDialog(view, "Transaksi berhasil dihapus dan stok dikembalikan.");

            bersih();
            loadMenu();
            tampilData();
        } else {
            JOptionPane.showMessageDialog(view, "Transaksi gagal dihapus.");
        }
    }
}
    private void isiFormDariTable() {
        int row = view.getTableTransaksi().getSelectedRow();

        if (row >= 0) {
            view.getTxtIdTransaksi().setText(view.getTableTransaksi().getValueAt(row, 0).toString());
            view.getTxtNamaPelanggan().setText(view.getTableTransaksi().getValueAt(row, 2).toString());
            view.getTxtHarga().setText(view.getTableTransaksi().getValueAt(row, 4).toString());
            view.getTxtJumlah().setText(view.getTableTransaksi().getValueAt(row, 5).toString());
            view.getTxtTotal().setText(view.getTableTransaksi().getValueAt(row, 6).toString());
            view.getTxtBayar().setText(view.getTableTransaksi().getValueAt(row, 7).toString());
            view.getTxtKembalian().setText(view.getTableTransaksi().getValueAt(row, 8).toString());
        }
    }

    private void bersih() {
        view.getTxtIdTransaksi().setText("");
        view.getTxtNamaPelanggan().setText("");
        view.getTxtJumlah().setText("");
        view.getTxtTotal().setText("");
        view.getTxtBayar().setText("");
        view.getTxtKembalian().setText("");
        view.getTxtCari().setText("");
        view.getTableTransaksi().clearSelection();

        if (view.getCmbMenu().getItemCount() > 0) {
            view.getCmbMenu().setSelectedIndex(0);
        }

        isiHargaDariMenu();
    }
}