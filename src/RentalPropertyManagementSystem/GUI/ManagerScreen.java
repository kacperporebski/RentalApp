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

    public ManagerScreen(){
        this.setContentPane(panel);
        this.pack();

        changeFeeScreen = new ChangeFeeScreen();
        summaryScreen = new SummaryReportScreen();

        requestSummaryReportButton.addActionListener(new SummaryReportActionListener());
    }

    public class SummaryReportActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            summaryScreen.setVisible(true);
        }
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
}
