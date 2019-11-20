package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegRenterScreen extends JFrame
{
    private JPanel panel;
    private JButton refreshButton;
    private JButton enterSearchCriteriaButton;
    private JScrollPane scrollPane;
    private JList propertyList;
    private JButton logoutButton;
    private JButton displayNotificationsButton;
    private JButton emailButton;
    private SearchCriteriaScreen searchCriteriaScreen;


    public RegRenterScreen()
    {
        this.setContentPane(panel);
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

    public JPanel getPanel()
    {
        return panel;
    }

    public JButton getRefreshButton()
    {
        return refreshButton;
    }

    public JButton getEnterSearchCriteriaButton()
    {
        return enterSearchCriteriaButton;
    }

    public JScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public JList getPropertyList()
    {
        return propertyList;
    }

    public JButton getLogoutButton()
    {
        return logoutButton;
    }

    public SearchCriteriaScreen getSearchCriteriaScreen()
    {
        return searchCriteriaScreen;
    }
}

