package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class ChangeListingScreen extends JFrame
{
    private JPanel panel;
    private JButton changeStateButton;
    private JComboBox comboBox1;
    private JButton cancelButton;

    public ChangeListingScreen()
    {
        this.setName("Change Listing");
        this.setContentPane(panel);
        this.pack();
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public JButton getChangeStateButton()
    {
        return changeStateButton;
    }

    public JComboBox getComboBox1()
    {
        return comboBox1;
    }

    public JButton getCancelButton()
    {
        return cancelButton;
    }
}
