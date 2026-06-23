package restoapp.view;

import restoapp.controller.LaporanController;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class LaporanTransaksiInternalFrame extends JInternalFrame {

    private JTable tableLaporan;
    private JTextField txtCari;
    private JButton btnCetak;
    private JButton btnRefresh;
    private JLabel lblTotalTransaksi;
    private JLabel lblTotalPendapatan;

    public LaporanTransaksiInternalFrame() {
        initComponents();
        new LaporanController(this);
    }

    private void initComponents() {
        setTitle("Laporan Penjualan / Transaksi");
        setSize(860, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setLayout(null);

        JLabel lblJudul = new JLabel("LAPORAN PENJUALAN / TRANSAKSI");
        lblJudul.setBounds(20, 10, 300, 25);
        add(lblJudul);

        JLabel lblCari = new JLabel("Cari");
        lblCari.setBounds(20, 50, 50, 25);
        add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(70, 50, 250, 25);
        add(txtCari);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(330, 50, 100, 25);
        add(btnRefresh);

        btnCetak = new JButton("Cetak Laporan");
        btnCetak.setBounds(440, 50, 140, 25);
        add(btnCetak);

        tableLaporan = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableLaporan);
        scrollPane.setBounds(20, 90, 810, 210);
        add(scrollPane);

        lblTotalTransaksi = new JLabel("Total Transaksi: 0");
        lblTotalTransaksi.setBounds(20, 315, 250, 25);
        add(lblTotalTransaksi);

        lblTotalPendapatan = new JLabel("Total Pendapatan: Rp 0");
        lblTotalPendapatan.setBounds(300, 315, 300, 25);
        add(lblTotalPendapatan);
    }

    public JTable getTableLaporan() {
        return tableLaporan;
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public JButton getBtnCetak() {
        return btnCetak;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JLabel getLblTotalTransaksi() {
        return lblTotalTransaksi;
    }

    public JLabel getLblTotalPendapatan() {
        return lblTotalPendapatan;
    }
}