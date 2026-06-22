package restoapp.view;

import restoapp.controller.LoginController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnKeluar;

    public LoginFrame() {
        initComponents();
        new LoginController(this);
    }

    private void initComponents() {
        setTitle("Login - RestoApp");
        setSize(400, 260);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel lblJudul = new JLabel("LOGIN RESTORAN");
        lblJudul.setBounds(135, 20, 200, 30);
        add(lblJudul);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 70, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 70, 180, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 110, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 180, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 155, 85, 30);
        add(btnLogin);

        btnKeluar = new JButton("Keluar");
        btnKeluar.setBounds(245, 155, 85, 30);
        add(btnKeluar);
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnKeluar() {
        return btnKeluar;
    }
}