package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    MenuScreen menuScreen;
    LoginScreen loginScreen;
    RegisterUserScreen regUserScreen;
    RenterScreen renterScreen;
    RegRenterScreen regRenterScreen;
    LandlordScreen landlordScreen;
    ManagerScreen managerScreen;

    public GUI()
    {
        menuScreen = new MenuScreen();
        loginScreen = new LoginScreen();
        regUserScreen = new RegisterUserScreen();
        renterScreen = new RenterScreen();
        regRenterScreen = new RegRenterScreen();
        landlordScreen = new LandlordScreen();
        managerScreen = new ManagerScreen();

        menuScreen.getLoginButton().addActionListener(new DisplayLoginScreenActionListener());
        menuScreen.getRegisterButton().addActionListener(new DisplayRegUserScreenActionListener());
        menuScreen.getContinueWithoutLoggingInButton().addActionListener(new DisplayRenterScreen());
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

    public class DisplayRenterScreen implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            renterScreen.setVisible(true);
            //menuScreen.setVisible(false);
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

    public RenterScreen getRenterScreen()
    {
        return renterScreen;
    }

    public RegRenterScreen getRegRenterScreen()
    {
        return regRenterScreen;
    }

    public LandlordScreen getLandlordScreen() {
        return landlordScreen;
    }

    public ManagerScreen getManagerScreen() {
        return managerScreen;
    }
}
