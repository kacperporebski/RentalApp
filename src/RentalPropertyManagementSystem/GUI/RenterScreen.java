package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenterScreen extends JFrame
{
    private JPanel renterPanel;
    private JList propertyList;

    private JButton refreshButton;
    private JButton enterSearchCriteriaButton;
    private SearchCriteriaScreen searchCriteriaScreen;

    public RenterScreen()
    {
        this.setContentPane(renterPanel);
        this.pack();
        searchCriteriaScreen = new SearchCriteriaScreen();

        enterSearchCriteriaButton.addActionListener(new ShowSearchCriteriaScreenActionListener());
    }

    public class ShowSearchCriteriaScreenActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            searchCriteriaScreen.setVisible(true);
        }
    }

    public JList getPropertyList()
    {
        return propertyList;
    }

    public JButton getRefreshButton()
    {
        return refreshButton;
    }

    public JButton getEnterSearchCriteriaButton()
    {
        return enterSearchCriteriaButton;
    }

    public SearchCriteriaScreen getSearchCriteriaScreen()
    {
        return searchCriteriaScreen;
    }
}
