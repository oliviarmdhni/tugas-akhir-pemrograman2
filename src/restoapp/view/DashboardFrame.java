package restoapp.view;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DashboardFrame extends JFrame {

    private JDesktopPane desktopPane;
    private JButton btnDataMenu;
    private JButton btnDataTransaksi;
    private JButton btnLogout;

    public DashboardFrame(String namaUser) {
        initComponents(namaUser);
    }

    private void initComponents(String namaUser) {
        setTitle("Dashboard - Restoran");
        setSize(950, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblJudul = new JLabel("APLIKASI CRUD RESTORAN");
        lblJudul.setBounds(20, 10, 300, 30);
        add(lblJudul);

        JLabel lblUser = new JLabel("Login sebagai: " + namaUser);
        lblUser.setBounds(700, 10, 220, 30);
        add(lblUser);

        btnDataMenu = new JButton("Data Menu");
        btnDataMenu.setBounds(20, 50, 130, 35);
        add(btnDataMenu);

        btnDataTransaksi = new JButton("Data Transaksi");
        btnDataTransaksi.setBounds(160, 50, 150, 35);
        add(btnDataTransaksi);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(320, 50, 100, 35);
        add(btnLogout);

        desktopPane = new JDesktopPane();
        desktopPane.setBounds(20, 100, 890, 430);
        add(desktopPane);

        btnDataMenu.addActionListener(e -> bukaMenuInternalFrame());

        btnDataTransaksi.addActionListener(e -> bukaTransaksiInternalFrame());

        btnLogout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin logout?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (konfirmasi == JOptionPane.YES_OPTION) {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
                dispose();
            }
        });
    }

    private void bukaMenuInternalFrame() {
        desktopPane.removeAll();

        MenuInternalFrame menuFrame = new MenuInternalFrame();
        desktopPane.add(menuFrame);
        menuFrame.setVisible(true);

        desktopPane.repaint();
        desktopPane.revalidate();
    }

    private void bukaTransaksiInternalFrame() {
        desktopPane.removeAll();

        TransaksiInternalFrame transaksiFrame = new TransaksiInternalFrame();
        desktopPane.add(transaksiFrame);
        transaksiFrame.setVisible(true);

        desktopPane.repaint();
        desktopPane.revalidate();
    }
}