package restoapp.controller;

import restoapp.dao.TransaksiDAO;
import restoapp.model.TransaksiModel;
import restoapp.dao.MenuDAO;
import restoapp.model.MenuModel;
import restoapp.view.TransaksiInternalFrame;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TransaksiController {

    private TransaksiInternalFrame view;
    private MenuDAO menuDAO;
    private List<MenuModel> listMenu;
    private TransaksiDAO transaksiDAO;

    public TransaksiController(TransaksiInternalFrame view) {
        this.view = view;
        this.menuDAO = new MenuDAO();
        this.transaksiDAO = new TransaksiDAO();

        loadMenu();
        tampilData();
        
        this.view.getCmbMenu().addActionListener(e -> isiHargaDariMenu());
        this.view.getBtnHitung().addActionListener(e -> hitungTotal());
        this.view.getBtnSimpan().addActionListener(e -> simpanTransaksi());
        
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

        if (index < 0) {
            JOptionPane.showMessageDialog(view, "Pilih menu dulu!");
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

            view.getTxtNamaPelanggan().setText("");
            view.getTxtJumlah().setText("");
            view.getTxtTotal().setText("");
            view.getTxtBayar().setText("");
            view.getTxtKembalian().setText("");

            loadMenu();
        } else {
            JOptionPane.showMessageDialog(view, "Transaksi gagal disimpan.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(view, "Jumlah dan bayar harus berupa angka!");
    }
}
}