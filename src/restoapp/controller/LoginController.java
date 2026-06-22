package restoapp.controller;

import restoapp.dao.UserDAO;
import restoapp.model.UserModel;
import restoapp.view.DashboardFrame;
import restoapp.view.LoginFrame;
import javax.swing.JOptionPane;

public class LoginController {

    private LoginFrame view;
    private UserDAO userDAO;

    public LoginController(LoginFrame view) {
        this.view = view;
        this.userDAO = new UserDAO();

        this.view.getBtnLogin().addActionListener(e -> login());
        this.view.getBtnKeluar().addActionListener(e -> System.exit(0));
    }

    private void login() {
        String username = view.getTxtUsername().getText().trim();       
        String password = new String(view.getTxtPassword().getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username dan password wajib diisi!");
            return;
        }

        UserModel user = userDAO.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(view, "Login berhasil. Selamat datang, " + user.getNama());

            DashboardFrame dashboard = new DashboardFrame(user.getNama());
            dashboard.setVisible(true);

            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Username atau password salah!");
        }
    }
}