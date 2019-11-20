package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCriteriaScreen extends JFrame
{
    private JCheckBox apartmentCheckBox;
    private JCheckBox attachedHouseCheckBox;
    private JCheckBox detachedHouseCheckBox;
    private JCheckBox townHouseCheckBox;
    private JCheckBox condoCheckBox;
    private JCheckBox duplexCheckBox;
    private JTextField bedroomLowerTextField;
    private JTextField bedroomUpperTextField;
    private JTextField bathroomLowerTextField;
    private JTextField bathroomUpperTextField;
    private JCheckBox furnishedCheckBox;
    private JCheckBox unfurnishedCheckBox;
    private JCheckBox SWCheckBox;
    private JCheckBox NWCheckBox;
    private JCheckBox SECheckBox;
    private JCheckBox NECheckBox;
    private JPanel panel;
    private JButton enterButton;
    private JButton cancelButton;
    private JButton subscribeButton;

    public SearchCriteriaScreen()
    {
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }


    public class CancelActionListener implements ActionListener{
        SearchCriteriaScreen search;
        public CancelActionListener(SearchCriteriaScreen s){
            search = s;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            search.setVisible(false);
        }
    }

    public JCheckBox getApartmentCheckBox()
    {
        return apartmentCheckBox;
    }

    public JCheckBox getAttachedHouseCheckBox()
    {
        return attachedHouseCheckBox;
    }

    public JCheckBox getDetachedHouseCheckBox()
    {
        return detachedHouseCheckBox;
    }

    public JCheckBox getTownHouseCheckBox()
    {
        return townHouseCheckBox;
    }

    public JCheckBox getCondoCheckBox()
    {
        return condoCheckBox;
    }

    public JCheckBox getDuplexCheckBox()
    {
        return duplexCheckBox;
    }

    public JTextField getBedroomLowerTextField()
    {
        return bedroomLowerTextField;
    }

    public JTextField getBedroomUpperTextField()
    {
        return bedroomUpperTextField;
    }

    public JTextField getBathroomLowerTextField()
    {
        return bathroomLowerTextField;
    }

    public JTextField getBathroomUpperTextField()
    {
        return bathroomUpperTextField;
    }

    public JCheckBox getFurnishedCheckBox()
    {
        return furnishedCheckBox;
    }

    public JCheckBox getUnfurnishedCheckBox()
    {
        return unfurnishedCheckBox;
    }

    public JCheckBox getSWCheckBox()
    {
        return SWCheckBox;
    }

    public JCheckBox getNWCheckBox()
    {
        return NWCheckBox;
    }

    public JCheckBox getSECheckBox()
    {
        return SECheckBox;
    }

    public JCheckBox getNECheckBox()
    {
        return NECheckBox;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public JButton getEnterButton()
    {
        return enterButton;
    }

    public JButton getCancelButton()
    {
        return cancelButton;
    }


    public JButton getSubscribeButton()
    {
        return subscribeButton;
    }
}
