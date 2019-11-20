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
        this.setVisible(true);

        registerNewPropertyButton.addActionListener(new registerNewPropertyActionListener());
        viewUnpaidFeesButton.addActionListener(new viewUnpaidFeesActionListener());
        openEmailsButton.addActionListener(new openEmailsActionListener());
        logoutButton.addActionListener(new logoutActionListener());
    }

    public class registerNewPropertyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    public class viewUnpaidFeesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        }
    }

    public class openEmailsActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}

public class  logoutActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
}
