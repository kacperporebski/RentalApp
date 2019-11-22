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

    private PayFeeScreen payFeeScreen;
    private SendEmailScreen sendEmailScreen;
    private RegRenterPayOrEmailScreen payOrEmailScreen;

    public RenterScreen()
    {
        this.setContentPane(renterPanel);
        this.pack();
        searchCriteriaScreen = new SearchCriteriaScreen();

        payOrEmailScreen = new RegRenterPayOrEmailScreen();
        payFeeScreen = new PayFeeScreen();
        sendEmailScreen = new SendEmailScreen();

        enterSearchCriteriaButton.addActionListener(new ShowSearchCriteriaScreenActionListener());

        payOrEmailScreen.getRentPropertyButton().addActionListener(new ShowPayFeeScreenActionListener());
        payOrEmailScreen.getEmailLandlordButton().addActionListener(new ShowEmailScreenActionListener());
    }

    public class ShowSearchCriteriaScreenActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            searchCriteriaScreen.setVisible(true);
        }
    }
    public class ShowEmailScreenActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            sendEmailScreen.setVisible(true);
        }
    }

    public class ShowPayFeeScreenActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            payFeeScreen.setVisible(true);
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

    public PayFeeScreen getPayFeeScreen() {
        return payFeeScreen;
    }

    public RegRenterPayOrEmailScreen getPayOrEmailScreen() {
        return payOrEmailScreen;
    }

    public SendEmailScreen getSendEmailScreen() {
        return sendEmailScreen;
    }
}
