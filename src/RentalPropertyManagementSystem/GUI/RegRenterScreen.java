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
    private JButton unsubscribeButton;
    private SearchCriteriaScreen searchCriteriaScreen;

    private PayFeeScreen payFeeScreen;
    private NotificationScreen notificationScreen;
    private RegRenterPayOrEmailScreen payOrEmailScreen;
    private SendEmailScreen sendEmailScreen;


    public RegRenterScreen()
    {
        this.setContentPane(regPanel);
        this.pack();
        searchCriteriaScreen = new SearchCriteriaScreen();
        payFeeScreen = new PayFeeScreen();
        payOrEmailScreen = new RegRenterPayOrEmailScreen();
        sendEmailScreen = new SendEmailScreen();
        notificationScreen = new NotificationScreen();

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

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

    public void setRefreshButton(JButton refreshButton)
    {
        this.refreshButton = refreshButton;
    }

    public void setEnterSearchCriteriaButton(JButton enterSearchCriteriaButton)
    {
        this.enterSearchCriteriaButton = enterSearchCriteriaButton;
    }

    public void setScrollPane(JScrollPane scrollPane)
    {
        this.scrollPane = scrollPane;
    }

    public void setPropertyList(JList propertyList)
    {
        this.propertyList = propertyList;
    }

    public void setLogoutButton(JButton logoutButton)
    {
        this.logoutButton = logoutButton;
    }

    public void setDisplayNotificationsButton(JButton displayNotificationsButton)
    {
        this.displayNotificationsButton = displayNotificationsButton;
    }

    public void setRegPanel(JPanel regPanel)
    {
        this.regPanel = regPanel;
    }

    public void setSearchCriteriaScreen(SearchCriteriaScreen searchCriteriaScreen)
    {
        this.searchCriteriaScreen = searchCriteriaScreen;
    }

    public void setPayFeeScreen(PayFeeScreen payFeeScreen)
    {
        this.payFeeScreen = payFeeScreen;
    }

    public NotificationScreen getNotificationScreen()
    {
        return notificationScreen;
    }

    public void setNotificationScreen(NotificationScreen notificationScreen)
    {
        this.notificationScreen = notificationScreen;
    }

    public RegRenterPayOrEmailScreen getPayOrEmailScreen()
    {
        return payOrEmailScreen;
    }

    public void setPayOrEmailScreen(RegRenterPayOrEmailScreen payOrEmailScreen)
    {
        this.payOrEmailScreen = payOrEmailScreen;
    }

    public SendEmailScreen getSendEmailScreen()
    {
        return sendEmailScreen;
    }

    public void setSendEmailScreen(SendEmailScreen sendEmailScreen)
    {
        this.sendEmailScreen = sendEmailScreen;
    }

    public JButton getUnsubscribeButton()
    {
        return unsubscribeButton;
    }

    public void setUnsubscribeButton(JButton unsubscribeButton)
    {
        this.unsubscribeButton = unsubscribeButton;
    }
}

