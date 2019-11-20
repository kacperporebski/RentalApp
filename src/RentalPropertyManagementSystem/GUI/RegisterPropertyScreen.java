package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class RegisterPropertyScreen extends JFrame
{
    private JComboBox propertyTypeComboBox;
    private JTextField bedroomTextField;
    private JTextField bathroomTextField;
    private JComboBox furnishedComboBox;
    private JComboBox CityQuadrantComboBox;
    private JButton registerPropertyButton;
    private JPanel panel;
    private JTextField costTextField;
    private JTextField addressTextField;

    public RegisterPropertyScreen()
    {
        this.setName("Register Property");
        this.pack();
        this.setContentPane(panel);
    }

    public JComboBox getPropertyTypeComboBox()
    {
        return propertyTypeComboBox;
    }

    public void setPropertyTypeComboBox(JComboBox propertyTypeComboBox)
    {
        this.propertyTypeComboBox = propertyTypeComboBox;
    }

    public JTextField getBedroomTextField()
    {
        return bedroomTextField;
    }

    public void setBedroomTextField(JTextField bedroomTextField)
    {
        this.bedroomTextField = bedroomTextField;
    }

    public JTextField getBathroomTextField()
    {
        return bathroomTextField;
    }

    public void setBathroomTextField(JTextField bathroomTextField)
    {
        this.bathroomTextField = bathroomTextField;
    }

    public JComboBox getFurnishedComboBox()
    {
        return furnishedComboBox;
    }

    public void setFurnishedComboBox(JComboBox furnishedComboBox)
    {
        this.furnishedComboBox = furnishedComboBox;
    }

    public JComboBox getCityQuadrantComboBox()
    {
        return CityQuadrantComboBox;
    }

    public void setCityQuadrantComboBox(JComboBox cityQuadrantComboBox)
    {
        CityQuadrantComboBox = cityQuadrantComboBox;
    }

    public JButton getRegisterPropertyButton()
    {
        return registerPropertyButton;
    }

    public void setRegisterPropertyButton(JButton registerPropertyButton)
    {
        this.registerPropertyButton = registerPropertyButton;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

    public JTextField getCostTextField()
    {
        return costTextField;
    }

    public void setCostTextField(JTextField costTextField)
    {
        this.costTextField = costTextField;
    }

    public JTextField getAddressTextField()
    {
        return addressTextField;
    }

    public void setAddressTextField(JTextField addressTextField)
    {
        this.addressTextField = addressTextField;
    }
}
