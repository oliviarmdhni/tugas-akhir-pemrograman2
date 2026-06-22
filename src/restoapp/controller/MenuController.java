package restoapp.controller;

import restoapp.dao.MenuDAO;
import restoapp.model.MenuModel;
import restoapp.view.MenuInternalFrame;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class MenuController {

    private MenuInternalFrame view;
    private MenuDAO menuDAO;

    public MenuController(MenuInternalFrame view) {
        this.view = view;
        this.menuDAO = new MenuDAO();

        this.view.getBtnSimpan().addActionListener(e -> simpan());
        this.view.getBtnUbah().addActionListener(e -> ubah());
        this.view.getBtnHapus().addActionListener(e -> hapus());
        this.view.getBtnBersih().addActionListener(e -> bersih());

        this.view.getTableMenu().addMouseListener(new java.awt.event.MouseAdapter() {
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

        tampilData();
    }

    private void tampilData() {
        isiTable(menuDAO.getAllMenu());
    }

    private void cariData() {
        String keyword = view.getTxtCari().getText().trim();

        if (keyword.isEmpty()) {
            tampilData();
        } else {
            isiTable(menuDAO.searchMenu(keyword));
        }
    }

    private void isiTable(List<MenuModel> listMenu) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama Menu");
        model.addColumn("Kategori");
        model.addColumn("Harga");
        model.addColumn("Stok");

        for (MenuModel menu : listMenu) {
            model.addRow(new Object[]{
                menu.getIdMenu(),
                menu.getNamaMenu(),
                menu.getKategori(),
                menu.getHarga(),
                menu.getStok()
            });
        }

        view.getTableMenu().setModel(model);

        view.getTableMenu().getColumnModel().getColumn(0).setPreferredWidth(50);
        view.getTableMenu().getColumnModel().getColumn(1).setPreferredWidth(180);
        view.getTableMenu().getColumnModel().getColumn(2).setPreferredWidth(120);
        view.getTableMenu().getColumnModel().getColumn(3).setPreferredWidth(100);
        view.getTableMenu().getColumnModel().getColumn(4).setPreferredWidth(70);
    }

    private void simpan() {
        try {
            String namaMenu = view.getTxtNamaMenu().getText().trim();
            String kategori = view.getCmbKategori().getSelectedItem().toString();
            String hargaText = view.getTxtHarga().getText().trim();
            String stokText = view.getTxtStok().getText().trim();

            if (namaMenu.isEmpty() || hargaText.isEmpty() || stokText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama menu, harga, dan stok wajib diisi!");
                return;
            }

            double harga = Double.parseDouble(hargaText);
            int stok = Integer.parseInt(stokText);

            MenuModel menu = new MenuModel();
            menu.setNamaMenu(namaMenu);
            menu.setKategori(kategori);
            menu.setHarga(harga);
            menu.setStok(stok);

            if (menuDAO.insertMenu(menu)) {
                JOptionPane.showMessageDialog(view, "Data menu berhasil disimpan.");
                bersih();
            } else {
                JOptionPane.showMessageDialog(view, "Data menu gagal disimpan.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga dan stok harus berupa angka!");
        }
    }

    private void ubah() {
        try {
            if (view.getTxtIdMenu().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data menu yang ingin diubah!");
                return;
            }

            String namaMenu = view.getTxtNamaMenu().getText().trim();
            String kategori = view.getCmbKategori().getSelectedItem().toString();
            String hargaText = view.getTxtHarga().getText().trim();
            String stokText = view.getTxtStok().getText().trim();

            if (namaMenu.isEmpty() || hargaText.isEmpty() || stokText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama menu, harga, dan stok wajib diisi!");
                return;
            }

            int idMenu = Integer.parseInt(view.getTxtIdMenu().getText().trim());
            double harga = Double.parseDouble(hargaText);
            int stok = Integer.parseInt(stokText);

            MenuModel menu = new MenuModel();
            menu.setIdMenu(idMenu);
            menu.setNamaMenu(namaMenu);
            menu.setKategori(kategori);
            menu.setHarga(harga);
            menu.setStok(stok);

            if (menuDAO.updateMenu(menu)) {
                JOptionPane.showMessageDialog(view, "Data menu berhasil diubah.");
                bersih();
            } else {
                JOptionPane.showMessageDialog(view, "Data menu gagal diubah.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga dan stok harus berupa angka!");
        }
    }

    private void hapus() {
        if (view.getTxtIdMenu().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data menu yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                view,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            int idMenu = Integer.parseInt(view.getTxtIdMenu().getText().trim());

            if (menuDAO.deleteMenu(idMenu)) {
                JOptionPane.showMessageDialog(view, "Data menu berhasil dihapus.");
                bersih();
            } else {
                JOptionPane.showMessageDialog(view, "Data menu gagal dihapus.");
            }
        }
    }

    private void isiFormDariTable() {
        int row = view.getTableMenu().getSelectedRow();

        if (row >= 0) {
            view.getTxtIdMenu().setText(view.getTableMenu().getValueAt(row, 0).toString());
            view.getTxtNamaMenu().setText(view.getTableMenu().getValueAt(row, 1).toString());
            view.getCmbKategori().setSelectedItem(view.getTableMenu().getValueAt(row, 2).toString());
            view.getTxtHarga().setText(view.getTableMenu().getValueAt(row, 3).toString());
            view.getTxtStok().setText(view.getTableMenu().getValueAt(row, 4).toString());
        }
    }

    private void bersih() {
        view.getTxtIdMenu().setText("");
        view.getTxtNamaMenu().setText("");
        view.getCmbKategori().setSelectedIndex(0);
        view.getTxtHarga().setText("");
        view.getTxtStok().setText("");
        view.getTxtCari().setText("");
        view.getTableMenu().clearSelection();

        tampilData();
    }
}