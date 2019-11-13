package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class MenuScreen extends JFrame
{
    private JButton loginButton;
    private JPanel panel;
    private JButton continueWithoutLoggingInButton;

    public MenuScreen()
    {
        this.setName("MenuScreen");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public JButton getLoginButton()
    {
        return loginButton;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public JButton getContinueWithoutLoggingInButton()
    {
        return continueWithoutLoggingInButton;
    }
}
