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
    private JPanel regPanel;
    private SearchCriteriaScreen searchCriteriaScreen;

    private PayFeeScreen payFeeScreen;


    public RegRenterScreen()
    {
        this.setContentPane(regPanel);
        this.pack();
        searchCriteriaScreen = new SearchCriteriaScreen();
        payFeeScreen = new PayFeeScreen();

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

    public JButton getDisplayNotificationsButton()
    {
        return displayNotificationsButton;
    }

    public JPanel getRegPanel()
    {
        return regPanel;
    }

    public PayFeeScreen getPayFeeScreen()
    {
        return payFeeScreen;
    }
}

