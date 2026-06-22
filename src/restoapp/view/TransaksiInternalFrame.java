package restoapp.view;

import restoapp.controller.TransaksiController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TransaksiInternalFrame extends JInternalFrame {

    private JTextField txtIdTransaksi;
    private JTextField txtNamaPelanggan;
    private JComboBox<String> cmbMenu;
    private JTextField txtHarga;
    private JTextField txtJumlah;
    private JTextField txtTotal;
    private JTextField txtBayar;
    private JTextField txtKembalian;
    private JTextField txtCari;

    private JButton btnHitung;
    private JButton btnSimpan;
    private JButton btnHapus;
    private JButton btnBersih;

    private JTable tableTransaksi;

    public TransaksiInternalFrame() {
        initComponents();
        new TransaksiController(this);
    }

    private void initComponents() {
        setTitle("Data Transaksi");
        setSize(900, 430);
        setLayout(null);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        JLabel lblId = new JLabel("ID Transaksi");
        lblId.setBounds(20, 20, 120, 25);
        add(lblId);

        txtIdTransaksi = new JTextField();
        txtIdTransaksi.setBounds(140, 20, 200, 25);
        txtIdTransaksi.setEditable(false);
        add(txtIdTransaksi);

        JLabel lblNama = new JLabel("Nama Pelanggan");
        lblNama.setBounds(20, 60, 120, 25);
        add(lblNama);

        txtNamaPelanggan = new JTextField();
        txtNamaPelanggan.setBounds(140, 60, 200, 25);
        add(txtNamaPelanggan);

        JLabel lblMenu = new JLabel("Pilih Menu");
        lblMenu.setBounds(20, 100, 120, 25);
        add(lblMenu);

        cmbMenu = new JComboBox<>();
        cmbMenu.setBounds(140, 100, 200, 25);
        add(cmbMenu);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(20, 140, 120, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(140, 140, 200, 25);
        txtHarga.setEditable(false);
        add(txtHarga);

        JLabel lblJumlah = new JLabel("Jumlah");
        lblJumlah.setBounds(20, 180, 120, 25);
        add(lblJumlah);

        txtJumlah = new JTextField();
        txtJumlah.setBounds(140, 180, 200, 25);
        add(txtJumlah);

        JLabel lblTotal = new JLabel("Total");
        lblTotal.setBounds(20, 220, 120, 25);
        add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(140, 220, 200, 25);
        txtTotal.setEditable(false);
        add(txtTotal);

        JLabel lblBayar = new JLabel("Bayar");
        lblBayar.setBounds(20, 260, 120, 25);
        add(lblBayar);

        txtBayar = new JTextField();
        txtBayar.setBounds(140, 260, 200, 25);
        add(txtBayar);

        JLabel lblKembalian = new JLabel("Kembalian");
        lblKembalian.setBounds(20, 300, 120, 25);
        add(lblKembalian);

        txtKembalian = new JTextField();
        txtKembalian.setBounds(140, 300, 200, 25);
        txtKembalian.setEditable(false);
        add(txtKembalian);

        btnHitung = new JButton("Hitung");
        btnHitung.setBounds(20, 345, 90, 30);
        add(btnHitung);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(115, 345, 90, 30);
        add(btnSimpan);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(210, 345, 90, 30);
        add(btnHapus);

        btnBersih = new JButton("Bersih");
        btnBersih.setBounds(305, 345, 90, 30);
        add(btnBersih);

        JLabel lblCari = new JLabel("Cari");
        lblCari.setBounds(430, 20, 50, 25);
        add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(480, 20, 360, 25);
        add(txtCari);

        tableTransaksi = new JTable();
        tableTransaksi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(tableTransaksi);
        scrollPane.setBounds(430, 60, 410, 315);
        add(scrollPane);
    }

    public JTextField getTxtIdTransaksi() {
        return txtIdTransaksi;
    }

    public JTextField getTxtNamaPelanggan() {
        return txtNamaPelanggan;
    }

    public JComboBox<String> getCmbMenu() {
        return cmbMenu;
    }

    public JTextField getTxtHarga() {
        return txtHarga;
    }

    public JTextField getTxtJumlah() {
        return txtJumlah;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JTextField getTxtBayar() {
        return txtBayar;
    }

    public JTextField getTxtKembalian() {
        return txtKembalian;
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public JButton getBtnHitung() {
        return btnHitung;
    }

    public JButton getBtnSimpan() {
        return btnSimpan;
    }

    public JButton getBtnHapus() {
        return btnHapus;
    }

    public JButton getBtnBersih() {
        return btnBersih;
    }

    public JTable getTableTransaksi() {
        return tableTransaksi;
    }
}