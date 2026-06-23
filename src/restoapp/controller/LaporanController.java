package restoapp.controller;

import restoapp.dao.TransaksiDAO;
import restoapp.model.TransaksiModel;
import restoapp.view.LaporanTransaksiInternalFrame;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class LaporanController {

    private LaporanTransaksiInternalFrame view;
    private TransaksiDAO transaksiDAO;

    public LaporanController(LaporanTransaksiInternalFrame view) {
        this.view = view;
        this.transaksiDAO = new TransaksiDAO();

        tampilData();

        this.view.getBtnRefresh().addActionListener(e -> tampilData());
        this.view.getBtnCetak().addActionListener(e -> cetakLaporan());

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

    private void tampilData() {
        List<TransaksiModel> listTransaksi = transaksiDAO.getAllTransaksi();
        isiTabel(listTransaksi);
    }

    private void cariData() {
        String keyword = view.getTxtCari().getText().trim();

        List<TransaksiModel> listTransaksi;

        if (keyword.isEmpty()) {
            listTransaksi = transaksiDAO.getAllTransaksi();
        } else {
            listTransaksi = transaksiDAO.searchTransaksi(keyword);
        }

        isiTabel(listTransaksi);
    }

    private void isiTabel(List<TransaksiModel> listTransaksi) {
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

        double totalPendapatan = 0;

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

            totalPendapatan += transaksi.getTotal();
        }

        view.getTableLaporan().setModel(model);
        aturLebarKolom();

        view.getLblTotalTransaksi().setText("Total Transaksi: " + listTransaksi.size());
        view.getLblTotalPendapatan().setText("Total Pendapatan: Rp " + totalPendapatan);
    }

    private void aturLebarKolom() {
        view.getTableLaporan().getColumnModel().getColumn(0).setPreferredWidth(50);
        view.getTableLaporan().getColumnModel().getColumn(1).setPreferredWidth(140);
        view.getTableLaporan().getColumnModel().getColumn(2).setPreferredWidth(130);
        view.getTableLaporan().getColumnModel().getColumn(3).setPreferredWidth(150);
        view.getTableLaporan().getColumnModel().getColumn(4).setPreferredWidth(90);
        view.getTableLaporan().getColumnModel().getColumn(5).setPreferredWidth(70);
        view.getTableLaporan().getColumnModel().getColumn(6).setPreferredWidth(100);
        view.getTableLaporan().getColumnModel().getColumn(7).setPreferredWidth(100);
        view.getTableLaporan().getColumnModel().getColumn(8).setPreferredWidth(100);
    }

    private void cetakLaporan() {
        try {
            MessageFormat header = new MessageFormat("Laporan Penjualan / Transaksi Restoran");
            MessageFormat footer = new MessageFormat("Halaman {0}");

            boolean berhasil = view.getTableLaporan().print(
                    JTable.PrintMode.FIT_WIDTH,
                    header,
                    footer
            );

            if (berhasil) {
                JOptionPane.showMessageDialog(view, "Laporan berhasil dicetak.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal mencetak laporan: " + e.getMessage());
        }
    }
}