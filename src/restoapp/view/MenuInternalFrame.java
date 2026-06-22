package restoapp.view;

import restoapp.controller.MenuController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MenuInternalFrame extends JInternalFrame {

    private JTextField txtIdMenu;
    private JTextField txtNamaMenu;
    private JComboBox<String> cmbKategori;
    private JTextField txtHarga;
    private JTextField txtStok;
    private JTextField txtCari;

    private JButton btnSimpan;
    private JButton btnUbah;
    private JButton btnHapus;
    private JButton btnBersih;

    private JTable tableMenu;

    public MenuInternalFrame() {
        initComponents();
        new MenuController(this);
    }

    private void initComponents() {
        setTitle("Data Menu Restoran");
        setSize(820, 400);
        setLayout(null);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        JLabel lblId = new JLabel("ID Menu");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);

        txtIdMenu = new JTextField();
        txtIdMenu.setBounds(130, 20, 200, 25);
        txtIdMenu.setEditable(false);
        add(txtIdMenu);

        JLabel lblNama = new JLabel("Nama Menu");
        lblNama.setBounds(20, 60, 100, 25);
        add(lblNama);

        txtNamaMenu = new JTextField();
        txtNamaMenu.setBounds(130, 60, 200, 25);
        add(txtNamaMenu);

        JLabel lblKategori = new JLabel("Kategori");
        lblKategori.setBounds(20, 100, 100, 25);
        add(lblKategori);

        cmbKategori = new JComboBox<>();
        cmbKategori.addItem("Makanan");
        cmbKategori.addItem("Minuman");
        cmbKategori.addItem("Snack");
        cmbKategori.addItem("Dessert");
        cmbKategori.setBounds(130, 100, 200, 25);
        add(cmbKategori);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(20, 140, 100, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(130, 140, 200, 25);
        add(txtHarga);

        JLabel lblStok = new JLabel("Stok");
        lblStok.setBounds(20, 180, 100, 25);
        add(lblStok);

        txtStok = new JTextField();
        txtStok.setBounds(130, 180, 200, 25);
        add(txtStok);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 235, 90, 30);
        add(btnSimpan);

        btnUbah = new JButton("Ubah");
        btnUbah.setBounds(120, 235, 90, 30);
        add(btnUbah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(220, 235, 90, 30);
        add(btnHapus);

        btnBersih = new JButton("Bersih");
        btnBersih.setBounds(120, 275, 90, 30);
        add(btnBersih);

        JLabel lblCari = new JLabel("Cari Menu");
        lblCari.setBounds(370, 20, 100, 25);
        add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(450, 20, 340, 25);
        add(txtCari);

        tableMenu = new JTable();
        tableMenu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(tableMenu);
        scrollPane.setBounds(370, 60, 420, 260);
        add(scrollPane);
    }

    public JTextField getTxtIdMenu() {
        return txtIdMenu;
    }

    public JTextField getTxtNamaMenu() {
        return txtNamaMenu;
    }

    public JComboBox<String> getCmbKategori() {
        return cmbKategori;
    }

    public JTextField getTxtHarga() {
        return txtHarga;
    }

    public JTextField getTxtStok() {
        return txtStok;
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public JButton getBtnSimpan() {
        return btnSimpan;
    }

    public JButton getBtnUbah() {
        return btnUbah;
    }

    public JButton getBtnHapus() {
        return btnHapus;
    }

    public JButton getBtnBersih() {
        return btnBersih;
    }

    public JTable getTableMenu() {
        return tableMenu;
    }
}