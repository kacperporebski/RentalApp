package RentalPropertyManagementSystem.GUI;

import RentalPropertyManagementSystem.Users.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerScreen extends JFrame
{
    private JPanel panel;
    private JButton requestSummaryReportButton;
    private JButton viewRentersButton;
    private JButton logoutButton;
    private JButton viewLandlordsButton;
    private JButton changeRegistrationFeeButton;
    private JButton changePropertyListingButton;

    private SummaryReportScreen summaryScreen;
    private ChangeFeeScreen changeFeeScreen;
    private PropertiesScreen propertiesScreen;
    private RenterListScreen renterList;
    private LandlordListScreen landlordList;

    public ManagerScreen(){
        this.setContentPane(panel);
        this.pack();

        changeFeeScreen = new ChangeFeeScreen();
        summaryScreen = new SummaryReportScreen();
        propertiesScreen = new PropertiesScreen();
        renterList = new RenterListScreen();
        landlordList = new LandlordListScreen();
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getChangePropertyListingButton() {
        return changePropertyListingButton;
    }

    public JButton getChangeRegistrationFeeButton() {
        return changeRegistrationFeeButton;
    }

    public JButton getRequestSummaryReportButton() {
        return requestSummaryReportButton;
    }

    public JButton getViewLandlordsButton() {
        return viewLandlordsButton;
    }

    public JButton getViewRentersButton() {
        return viewRentersButton;
    }

    public ChangeFeeScreen getChangeFeeScreen() {
        return changeFeeScreen;
    }

    public SummaryReportScreen getSummaryScreen() {
        return summaryScreen;
    }

    public PropertiesScreen getPropertiesScreen() {
        return propertiesScreen;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public RenterListScreen getRenterList() {
        return renterList;
    }

    public LandlordListScreen getLandlordList() {
        return landlordList;
    }
}
