package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class LoginScreen extends JFrame
{
    private JPanel panel1;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JTextField passwordTextField;

    public LoginScreen()
    {
        this.setName("LoginScreen");
        this.setContentPane(panel1);
        this.pack();
    }

    public JPanel getPanel1()
    {
        return panel1;
    }

    public JTextField getUsernameTextField()
    {
        return usernameTextField;
    }

    public JLabel getUsernameLabel()
    {
        return usernameLabel;
    }

    public JLabel getPasswordLabel()
    {
        return passwordLabel;
    }

    public JTextField getPasswordTextField()
    {
        return passwordTextField;
    }

    public JButton getLoginButton()
    {
        return loginButton;
    }
}
