package Client.GUI;

import javax.swing.*;

public class LoginScreen extends JFrame
{
    private JPanel panel1;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public LoginScreen()
    {
        this.setName("LoginScreen");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
