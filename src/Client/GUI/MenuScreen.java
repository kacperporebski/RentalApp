package Client.GUI;

import Users.AccountHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        loginButton.addActionListener(new LoginScreenButtonActionListener());
    }

    public class LoginScreenButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            LoginScreen loginScreen = new LoginScreen();
        }
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
