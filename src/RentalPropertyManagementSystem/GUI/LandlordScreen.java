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
    private JButton openEmailsButton;
    private JButton logoutButton;

    private RegisterPropertyScreen regPropertyScreen;
   /*
      private EmailScreen emailsScreen;
      private FeeScreen feeScreen;
*/
    public LandlordScreen(){
        this.setName("Landlord Screen");
        this.setContentPane(landlordPanel);
        this.pack();

        regPropertyScreen = new RegisterPropertyScreen();

        registerNewPropertyButton.addActionListener(new RegPropertyActionListener());
        viewUnpaidFeesButton.addActionListener(new UnpaidFeesActionListener());
        openEmailsButton.addActionListener(new EmailsActionListener());
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
            // feeScreen.setVisible(true);
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

    public JButton getOpenEmailsButton() {
        return openEmailsButton;
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
}
