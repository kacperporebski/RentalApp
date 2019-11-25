package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.Users.AccountHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManagerController extends Controller
{
    public ManagerController(GUI view, RenterWebsite website)
    {
        super(view, website);

        view.getManagerScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getManagerScreen().getChangeRegistrationFeeButton().addActionListener(new ChangeRegFeeActionListener());
        view.getManagerScreen().getRequestSummaryReportButton().addActionListener(new RequestSummaryReportActionListener());
        view.getManagerScreen().getChangePropertyListingButton().addActionListener(new ManagePropertiesActionListener());
        view.getManagerScreen().getChangeFeeScreen().getChangeFeeButton().addActionListener(new ChangeFeeActionListener());
        view.getManagerScreen().getPropertiesScreen().getProperties().addMouseListener(new DoubleClickDisplayChangeListing());
        view.getManagerScreen().getViewRentersButton().addActionListener(new ViewRentersActionListener());
        view.getManagerScreen().getViewLandlordsButton().addActionListener(new ViewLandlordsActionListener());
    }

    public void displaySummaryProperties(JList list, ArrayList<Property> propertyList)
    {
        list.setModel(renterWebsite.propertyRepo.toStringSummaryList(propertyList));
        System.out.println("Displaying properties");
    }

    public void displayUsers(JList list, ArrayList<AccountHolder> renterList)
    {
        list.setModel(renterWebsite.userRepo.toStringList(renterList));
        System.out.println("Displaying users");
    }


    public class ChangeRegFeeActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            view.getManagerScreen().getChangeFeeScreen().getOldFee().setText(renterWebsite.propertyRepo.getAllProperties().get(0).getRegistrationFee().toString());
            view.getManagerScreen().getChangeFeeScreen().setVisible(true);
        }
    }

    public class ChangeFeeActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            for(Property p : renterWebsite.propertyRepo.getAllProperties()) {
                Fee fee = new Fee(Double.parseDouble(view.getManagerScreen().getChangeFeeScreen().getNewFee().getText()));
                fee.setPaymentDate(p.getRegistrationFee().getPaymentDate());
                fee.setPeriod(p.getRegistrationFee().getPeriod());
                p.setRegistrationFee(fee);
            }
            view.getManagerScreen().getChangeFeeScreen().setVisible(false);
        }
    }

    public class ManagePropertiesActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            displayProperties(view.getManagerScreen().getPropertiesScreen().getProperties(),renterWebsite.propertyRepo.getAllProperties());
            view.getManagerScreen().getPropertiesScreen().setVisible(true);
        }
    }

    public class RequestSummaryReportActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int active = 0;
            int listed = 0;
            int rented = 0;

            ArrayList<Property> housesRented = new ArrayList<>();

            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();
            Period period = new Period(currentMonth, currentYear);

            for(Property p : renterWebsite.propertyRepo.getAllProperties()){
                if(p.getState() == STATE.ACTIVE)
                    active++;
                if(p.getDateRegistered().getMonth() == currentMonth && p.getDateRegistered().getYear() == currentYear)
                    listed++;
                if(p.getDateRented() != null && p.getDateRented().getMonth() == currentMonth && p.getDateRented().getYear() == currentYear) {
                    rented++;
                    housesRented.add(p);
                }
            }

            SummaryReport sum = new SummaryReport(listed,rented,active,housesRented, period);
            view.getManagerScreen().getSummaryScreen().getSummaryInfo().setText(sum.numbersToString());
            displaySummaryProperties(view.getManagerScreen().getSummaryScreen().getPropertyList(), sum.getListedHouses());
            System.out.println("Displaying summary report");

            view.getManagerScreen().getSummaryScreen().setVisible(true);
        }
    }

    public class ViewRentersActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            displayUsers(view.getManagerScreen().getRenterList().getRenters(), renterWebsite.userRepo.getAllRenters());
            view.getManagerScreen().getRenterList().setVisible(true);
        }
    }

    public class ViewLandlordsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            displayUsers(view.getManagerScreen().getLandlordList().getLandlords(), renterWebsite.userRepo.getAllLandlords());
            view.getManagerScreen().getLandlordList().setVisible(true);
        }
    }

    public class DoubleClickDisplayChangeListing extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {

            if(e.getClickCount() == 2)
            {
                int index = view.getManagerScreen().getPropertiesScreen().getProperties().getSelectedIndex();

                for(ActionListener t : view.getManagerScreen().getPropertiesScreen().getChangeListing().getChangeStateButton().getActionListeners())
                {
                    view.getManagerScreen().getPropertiesScreen().getChangeListing().getChangeStateButton().removeActionListener(t);
                }
                view.getManagerScreen().getPropertiesScreen().getChangeListing().getChangeStateButton().addActionListener(new ChangeListingActionListener(index));
                view.getManagerScreen().getPropertiesScreen().getChangeListing().setVisible(true);
            }
        }
    }

    public class ChangeListingActionListener implements ActionListener{
        int index;

        public ChangeListingActionListener(int i){
            index = i;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            STATE changeState = STATE.valueOf(view.getManagerScreen().getPropertiesScreen().getChangeListing().getComboBox1().getSelectedItem().toString());
            renterWebsite.propertyRepo.getAllProperties().get(index).setState(changeState);

            displayProperties(view.getManagerScreen().getPropertiesScreen().getProperties(), renterWebsite.propertyRepo.getAllProperties());
            view.getManagerScreen().getPropertiesScreen().setVisible(true);
            view.getManagerScreen().getPropertiesScreen().getChangeListing().setVisible(false);
        }
    }
}
