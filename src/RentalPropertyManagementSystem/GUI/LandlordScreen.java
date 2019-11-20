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
    private JButton logoutButton;

   /* private RegPropertyScreen regPropertyScreen;
      private FeeScreen feeScreen;
*/
    public LandlordScreen(){
        this.setName("Landlord Screen");
        this.setContentPane(landlordPanel);
        this.pack();

        registerNewPropertyButton.addActionListener(new RegPropertyActionListener());
        viewUnpaidFeesButton.addActionListener(new UnpaidFeesActionListener());
    }

    public class RegPropertyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           // regPropertyScreen.setVisible(true);
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

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getRegisterNewPropertyButton() {
        return registerNewPropertyButton;
    }

    public JButton getViewUnpaidFeesButton() {
        return viewUnpaidFeesButton;
    }
}
