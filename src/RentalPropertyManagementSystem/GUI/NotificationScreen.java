package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class NotificationScreen extends JFrame
{
    private JButton refreshButton;
    private JButton clearNotificationsButton;
    private JList notificationList;
    private JPanel panel;
    private JButton removeSelectedButton;

    public NotificationScreen()
    {
        this.setName("Notifications");
        this.setContentPane(panel);
        this.pack();
    }

    public JButton getRefreshButton()
    {
        return refreshButton;
    }

    public void setRefreshButton(JButton refreshButton)
    {
        this.refreshButton = refreshButton;
    }

    public JButton getClearNotificationsButton()
    {
        return clearNotificationsButton;
    }

    public void setClearNotificationsButton(JButton clearNotificationsButton)
    {
        this.clearNotificationsButton = clearNotificationsButton;
    }

    public JList getNotificationList()
    {
        return notificationList;
    }

    public void setNotificationList(JList notificationList)
    {
        this.notificationList = notificationList;
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
