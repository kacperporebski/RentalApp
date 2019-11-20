package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandlordScreen extends JFrame
{
    private JPanel landlordPanel;
    private JList list1;
    private JButton registerNewPropertyButton;
    private JButton viewUnpaidFeesButton;
    private JButton openEmailsButton;
    private JButton logoutButton;

    public LandlordScreen(){
        this.setName("Landlord Screen");
        this.setContentPane(landlordPanel);
        this.pack();
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
}
