package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    MenuScreen menuScreen;
    LoginScreen loginScreen;
    RegisterUserScreen regUserScreen;

    public GUI()
    {
        menuScreen = new MenuScreen();
        loginScreen = new LoginScreen();
        regUserScreen = new RegisterUserScreen();

        menuScreen.getLoginButton().addActionListener(new DisplayLoginScreenActionListener());
        menuScreen.getRegisterButton().addActionListener(new DisplayRegUserScreenActionListener());

    }

    public class DisplayLoginScreenActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            loginScreen.setVisible(true);
        }
    }

    public class DisplayRegUserScreenActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            regUserScreen.setVisible(true);
        }
    }

    public MenuScreen getMenuScreen()
    {
        return menuScreen;
    }

    public LoginScreen getLoginScreen()
    {
        return loginScreen;
    }

    public void setLoginScreen(LoginScreen loginScreen)
    {
        this.loginScreen = loginScreen;
    }

    public RegisterUserScreen getRegUserScreen()
    {
        return regUserScreen;
    }
}
