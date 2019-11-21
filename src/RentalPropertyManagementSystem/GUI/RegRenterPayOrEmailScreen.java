package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class RegRenterPayOrEmailScreen extends JFrame
{
    private JTextArea propertyInfoTextArea;
    private JButton rentPropertyButton;
    private JButton emailLandlordButton;
    private JPanel panel;

    public RegRenterPayOrEmailScreen()
    {
        this.setContentPane(panel);
        this.pack();
    }

    public JTextArea getPropertyInfoTextArea()
    {
        return propertyInfoTextArea;
    }

    public void setPropertyInfoTextArea(JTextArea propertyInfoTextArea)
    {
        this.propertyInfoTextArea = propertyInfoTextArea;
    }

    public JButton getRentPropertyButton()
    {
        return rentPropertyButton;
    }

    public void setRentPropertyButton(JButton rentPropertyButton)
    {
        this.rentPropertyButton = rentPropertyButton;
    }

    public JButton getEmailLandlordButton()
    {
        return emailLandlordButton;
    }

    public void setEmailLandlordButton(JButton emailLandlordButton)
    {
        this.emailLandlordButton = emailLandlordButton;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }
}
