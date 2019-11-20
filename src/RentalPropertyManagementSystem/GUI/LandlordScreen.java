package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandlordScreen extends JFrame
{
    private JPanel landlordPanel;
    private JList propertyList;
    private JButton registerNewPropertyButton;
    private JButton viewUnpaidFeesButton;
    private JButton logoutButton;
    private JButton refreshButton;

    private RegisterPropertyScreen regPropertyScreen;
    private UnpaidFeeScreen unpaidFeeScreen;

    public LandlordScreen(){
        this.setName("Landlord Screen");
        this.setContentPane(landlordPanel);
        this.pack();

        regPropertyScreen = new RegisterPropertyScreen();
        unpaidFeeScreen = new UnpaidFeeScreen();

        registerNewPropertyButton.addActionListener(new RegPropertyActionListener());
        viewUnpaidFeesButton.addActionListener(new UnpaidFeesActionListener());
    }

    public class RegPropertyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           regPropertyScreen.setVisible(true);
        }
    }

    public class UnpaidFeesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            unpaidFeeScreen.setVisible(true);
        }
    }

    public class EmailsActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // emailsScreen.setVisible(true);
        }
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getRegisterNewPropertyButton() {
        return registerNewPropertyButton;
    }

    public JButton getViewUnpaidFeesButton() {
        return viewUnpaidFeesButton;
    }

    public JList getPropertyList()
    {
        return propertyList;
    }

    public RegisterPropertyScreen getRegPropertyScreen()
    {
        return regPropertyScreen;
    }

    public JPanel getLandlordPanel()
    {
        return landlordPanel;
    }

    public UnpaidFeeScreen getUnpaidFeeScreen()
    {
        return unpaidFeeScreen;
    }

    public void setLandlordPanel(JPanel landlordPanel)
    {
        this.landlordPanel = landlordPanel;
    }

    public void setPropertyList(JList propertyList)
    {
        this.propertyList = propertyList;
    }

    public void setRegisterNewPropertyButton(JButton registerNewPropertyButton)
    {
        this.registerNewPropertyButton = registerNewPropertyButton;
    }

    public void setViewUnpaidFeesButton(JButton viewUnpaidFeesButton)
    {
        this.viewUnpaidFeesButton = viewUnpaidFeesButton;
    }

    public void setLogoutButton(JButton logoutButton)
    {
        this.logoutButton = logoutButton;
    }

    public JButton getRefreshButton()
    {
        return refreshButton;
    }

    public void setRefreshButton(JButton refreshButton)
    {
        this.refreshButton = refreshButton;
    }

    public void setRegPropertyScreen(RegisterPropertyScreen regPropertyScreen)
    {
        this.regPropertyScreen = regPropertyScreen;
    }

    public void setUnpaidFeeScreen(UnpaidFeeScreen unpaidFeeScreen)
    {
        this.unpaidFeeScreen = unpaidFeeScreen;
    }
}
