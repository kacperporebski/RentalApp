package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.GUI.RegisterPropertyScreen;
import RentalPropertyManagementSystem.GUI.SearchCriteriaScreen;
import RentalPropertyManagementSystem.Users.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller class which communicates with the GUI and the RenterWebsite
 */
public class RPMSController
{
    GUI view;
    RenterWebsite renterWebsite;
    Optional<AccountHolder> currentUser;

    Renter renter;

    public RPMSController(GUI view, RenterWebsite website) {
        this.view = view;
        renterWebsite = website;

        renter = new Renter();

        //TODO add ActionListeners to view...
        view.getLoginScreen().getLoginButton().addActionListener(new LoginActionListener());

        view.getRegUserScreen().getRegisterButton().addActionListener(new RegisterUserActionListener());

        view.getRegRenterScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getRegRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());
        view.getRegRenterScreen().getSearchCriteriaScreen().getEnterButton().addActionListener(new EnterSearchCriteria());
        view.getRegRenterScreen().getPropertyList().addMouseListener(new DoubleClickRentRentOrEmail());
        view.getRegRenterScreen().getDisplayNotificationsButton().addActionListener(new DisplayNotificationsActionListener());

        view.getRenterScreen().getSearchCriteriaScreen().getEnterButton().addActionListener(new EnterSearchCriteria());
        view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());
        view.getRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRenterScreen().getPropertyList().addMouseListener(new DoubleClickRentRentOrEmail());

        view.getLandlordScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getLandlordScreen().getRegPropertyScreen().getRegisterPropertyButton().addActionListener(new RegisterProperty());
        view.getLandlordScreen().getRefreshButton().addActionListener(new DisplayLandlordProperties());
        view.getLandlordScreen().getUnpaidFeeScreen().getRefreshButton().addActionListener(new DisplayUnpaidFees());
        view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().addMouseListener(new PayUnpaidFees());
        view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new PayFeeButtonListener());

        view.getManagerScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getManagerScreen().getChangeRegistrationFeeButton().addActionListener(new ChangeRegFeeActionListener());
        view.getManagerScreen().getRequestSummaryReportButton().addActionListener(new RequestSummaryReportActionListener());
        view.getManagerScreen().getChangePropertyListingButton().addActionListener(new ManagePropertiesActionListener());
        view.getManagerScreen().getChangeFeeScreen().getChangeFeeButton().addActionListener(new ChangeFeeActionListener());
        view.getManagerScreen().getPropertiesScreen().getProperties().addMouseListener(new DoubleClickDisplayChangeListing());
        view.getManagerScreen().getViewRentersButton().addActionListener(new ViewRentersActionListener());
        view.getManagerScreen().getViewLandlordsButton().addActionListener(new ViewLandlordsActionListener());
    }

    /**
     * ActionListener which validates the user existence, then logs into the system
     */
    public class LoginActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String username = view.getLoginScreen().getUsernameTextField().getText();
            String password = view.getLoginScreen().getPasswordTextField().getText();
            System.out.println(password);

            Optional<AccountHolder> user = renterWebsite.userRepo.login(username, password);
            //TODO switch case for which display to show depending on usertype
            if(user.isPresent())
            {
                currentUser = user;
                System.out.println("Logged in as " + currentUser.toString());

                switch (user.get().getAccountType())
                {
                    case Landlord:
                        view.getLandlordScreen().setVisible(true);
                        break;
                    case Manager:
                        view.getManagerScreen().setVisible(true);
                        break;
                    case RegRenter:
                        view.getRegRenterScreen().setVisible(true);
                        break;
                }

                view.getMenuScreen().setVisible(false);
                view.getLoginScreen().setVisible(false);
            }
            else
            {
                System.out.println("Couldn't login");
            }
        }
    }

    public class LogoutActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            view.getLandlordScreen().setVisible(false);
            view.getRegRenterScreen().setVisible(false);
            view.getManagerScreen().setVisible(false);

            currentUser.equals(Optional.empty());
            System.out.println("Logged out");
            view.getMenuScreen().setVisible(true);
        }
    }


    /**
     * ActionListener which
     */
    public class RegisterUserActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getRegUserScreen().getUsernameTextField().getText();
            String password = view.getRegUserScreen().getPasswordTextField().getText();
            UserType type = UserType.valueOf(view.getRegUserScreen().getAccountTypeBox().getSelectedItem().toString());
            Account account = new Account(username, password, type);
            String firstname = view.getRegUserScreen().getFirstNameTextField().getText();
            String lastname = view.getRegUserScreen().getLastNameTextField().getText();
            String email = view.getRegUserScreen().getEmailTextField().getText();

            if(username.equals("") || password.equals("") || firstname.equals("") || lastname.equals("") || email.equals("")) {
                view.getRegUserScreen().getError().setVisible(true);
                return;
            }

            AccountHolder user;
            switch(account.getAccountType())
            {
                case Manager:
                    user = new Manager(firstname, lastname, email, account);
                    break;
                case Landlord:
                    user = new Landlord(firstname, lastname, email, account);
                    break;
                case RegRenter:
                    user = new RegisteredRenter(firstname, lastname, email, account);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + account.getAccountType());
            }



            boolean added = renterWebsite.userRepo.addUser(user);
            if(added) {
                System.out.println("Added user " + user.toString());
                System.out.println("Adding user to database");
                renterWebsite.getMyDatabase().getUserDatabase().addUser(user);
                view.getRegUserScreen().setVisible(false);

                if(renter.getSearchCriteria() != null) {
                    System.out.printf("Search Criteria " + renter.getSearchCriteria().toString());
                    ((RegisteredRenter) user).setSearchCriteria(renter.getSearchCriteria());
                    view.getRenterScreen().getSearchCriteriaScreen().setVisible(false);
                    renterWebsite.propertyRepo.registerObserver(((RegisteredRenter) user));
                }

                view.getLoginScreen().setVisible(true);
            }
            else
                System.out.println("User already exists " + user.toString());

            renter.setSearchCriteria(null);
        }
    }



    //Todo implement registration class and call from here
    public class RegisterRenterActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    public class ChangeRegFeeActionListener implements  ActionListener{
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

    public class ListPropertiesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == view.getRenterScreen().getRefreshButton())
                 displayProperties(view.getRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
            else if(e.getSource() == view.getRegRenterScreen().getRefreshButton())
                displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
        }
    }

    public class ManagePropertiesActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            displayProperties(view.getManagerScreen().getPropertiesScreen().getProperties(),renterWebsite.propertyRepo.getAllProperties());
            view.getManagerScreen().getPropertiesScreen().setVisible(true);
        }
    }

    public void displayProperties(JList list, ArrayList<Property> propertyList)
    {
        list.setModel(renterWebsite.propertyRepo.toStringList(propertyList));
        System.out.println("Displaying properties");
    }

    public void displayNotifications(JList list, ArrayList<Notification> notificationList)
    {
        list.setModel(renterWebsite.userRepo.toStringNotificationList(notificationList));
        System.out.println("Displaying properties");
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

    public class SubscribeSearchCriteria implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            SearchCriteria criteria;
            //If Registered Renter:
            if(e.getSource() == view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                criteria = createCriteria(view.getRegRenterScreen().getSearchCriteriaScreen());

                System.out.printf("Search Criteria " + criteria.toString());
                ((RegisteredRenter)currentUser.get()).setSearchCriteria(criteria);
                view.getRegRenterScreen().getSearchCriteriaScreen().setVisible(false);
                renterWebsite.propertyRepo.registerObserver(((RegisteredRenter)currentUser.get()));
                renterWebsite.getMyDatabase().getSearchCriteriaDatabase().addSearchCriteria((RegisteredRenter)currentUser.get());
            }
            //If Regular Renter:
            else if(e.getSource() == view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                //Todo figure out how to convert renter into a registered renter

                criteria = new SearchCriteria(createCriteria(view.getRenterScreen().getSearchCriteriaScreen()));
                renter.setSearchCriteria(criteria);

                System.out.println("Must first register\n");
                view.getRegUserScreen().setVisible(true);
                view.getRegUserScreen().getAccountTypeBox().setSelectedIndex(2);
                view.getRenterScreen().getSearchCriteriaScreen().setVisible(false);
                view.getRenterScreen().setVisible(false);
            }
        }
    }

    public class DoubleClickRentRentOrEmail extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(e.getClickCount() == 2)
            {

                if(e.getSource() == view.getRegRenterScreen().getPropertyList()) {
                    for (ActionListener t : view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().getActionListeners()) {
                        view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().removeActionListener(t);
                    }
                    int index = view.getRegRenterScreen().getPropertyList().getSelectedIndex();

                    view.getRegRenterScreen().getPayOrEmailScreen().getPropertyInfoTextArea().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).toString());
                    view.getRegRenterScreen().getPayFeeScreen().getTextArea1().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).getRent().toString());
                    view.getRegRenterScreen().getPayOrEmailScreen().setVisible(true);

                    view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new RegRenterPayProperty(index));
                    view.getRegRenterScreen().getSendEmailScreen().getSender().setText(currentUser.get().getEmail());
                } else if (e.getSource() == view.getRenterScreen().getPropertyList()){
                    for (ActionListener t : view.getRenterScreen().getPayFeeScreen().getPayFeeButton().getActionListeners()) {
                        view.getRenterScreen().getPayFeeScreen().getPayFeeButton().removeActionListener(t);
                    }
                    int index = view.getRenterScreen().getPropertyList().getSelectedIndex();

                    view.getRenterScreen().getPayOrEmailScreen().getPropertyInfoTextArea().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).toString());
                    view.getRenterScreen().getPayFeeScreen().getTextArea1().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).getRent().toString());
                    view.getRenterScreen().getPayOrEmailScreen().setVisible(true);

                    view.getRenterScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new RegRenterPayProperty(index));
                }
            }
        }
    }

    public class RegRenterPayProperty implements ActionListener
    {
        int index;
        public RegRenterPayProperty(int index){this.index = index;}
        @Override
        public void actionPerformed(ActionEvent e)
        {
            renterWebsite.propertyRepo.getAllActiveProperties().get(index).getRent().isPaid();
            renterWebsite.propertyRepo.getAllActiveProperties().get(index).setState(STATE.RENTED);
            renterWebsite.propertyRepo.getAllActiveProperties().get(index).setDateRented(new Date());
            displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
            view.getRegRenterScreen().getPayFeeScreen().setVisible(false);
            view.getRegRenterScreen().getPayOrEmailScreen().setVisible(false);
        }
    }

    public class DisplayNotificationsActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Notification> notificationList = ((RegisteredRenter)currentUser.get()).getNotifications();
            displayNotifications(view.getRegRenterScreen().getNotificationScreen().getNotificationList(), notificationList);
            view.getRegRenterScreen().getNotificationScreen().setVisible(true);
        }
    }

    public class EnterSearchCriteria implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            SearchCriteria criteria;
            if(e.getSource() == view.getRegRenterScreen().getSearchCriteriaScreen().getEnterButton())
            {
                criteria = createCriteria(view.getRegRenterScreen().getSearchCriteriaScreen());
                displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getMatchingProperties(criteria));
                view.getRegRenterScreen().getSearchCriteriaScreen().setVisible(false);
            }
            //If Regular Renter:
            else if(e.getSource() == view.getRenterScreen().getSearchCriteriaScreen().getEnterButton())
            {
                //Todo figure out how to convert renter into a registered renter
                criteria = createCriteria(view.getRenterScreen().getSearchCriteriaScreen());
                displayProperties(view.getRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getMatchingProperties(criteria));
                view.getRenterScreen().getSearchCriteriaScreen().setVisible(false);
            }

        }
    }

    public SearchCriteria createCriteria(SearchCriteriaScreen frame)
    {
        ArrayList<PropertyType> propertyTypes = new ArrayList<>();
        ArrayList<Integer> bedrooms = new ArrayList<>();
        ArrayList<Integer> bathrooms = new ArrayList<>();
        boolean furnished;
        boolean unfurnished;
        ArrayList<CityQuadrants> cityQuadrants = new ArrayList<>();

        if(frame.getApartmentCheckBox().isSelected())
            propertyTypes.add(PropertyType.Apartment);
        if(frame.getAttachedHouseCheckBox().isSelected())
            propertyTypes.add(PropertyType.AttachedHouse);
        if(frame.getDetachedHouseCheckBox().isSelected())
            propertyTypes.add(PropertyType.DetachedHouse);
        if(frame.getTownHouseCheckBox().isSelected())
            propertyTypes.add(PropertyType.TownHouse);
        if(frame.getCondoCheckBox().isSelected())
            propertyTypes.add(PropertyType.Condo);
        if(frame.getDuplexCheckBox().isSelected())
            propertyTypes.add(PropertyType.Duplex);
        if(propertyTypes.isEmpty()){
            propertyTypes.add(PropertyType.Apartment);
            propertyTypes.add(PropertyType.AttachedHouse);
            propertyTypes.add(PropertyType.DetachedHouse);
            propertyTypes.add(PropertyType.TownHouse);
            propertyTypes.add(PropertyType.Condo);
            propertyTypes.add(PropertyType.Duplex);
        }

        Integer lowerBedrooms;
        Integer upperBedrooms;
        Integer lowerBathrooms;
        Integer upperBathrooms;
        try {
             lowerBedrooms = Integer.parseInt(frame.getBedroomLowerTextField().getText());
        } catch(NumberFormatException e) {
            lowerBedrooms = 0;
        }
        try{
            upperBedrooms = Integer.parseInt(frame.getBedroomUpperTextField().getText());
        } catch(NumberFormatException e){
            upperBedrooms = 100;
        }

        try{
            lowerBathrooms = Integer.parseInt(frame.getBathroomLowerTextField().getText());
        } catch(NumberFormatException e){
            lowerBathrooms = 0;
        }

        try{
            upperBathrooms = Integer.parseInt(frame.getBathroomUpperTextField().getText());
        } catch(NumberFormatException e){
            upperBathrooms = 100;
        }

        bedrooms.add(lowerBedrooms);
        bedrooms.add(upperBedrooms);
        bathrooms.add(lowerBathrooms);
        bathrooms.add(upperBathrooms);

        if(frame.getFurnishedCheckBox().isSelected())
            furnished = true;
        else
            furnished = false;

        if(frame.getUnfurnishedCheckBox().isSelected())
            unfurnished = true;
        else
            unfurnished = false;

        if(!unfurnished && !furnished) {
            furnished = true;
            unfurnished = true;
        }

        if(frame.getSWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SW);
        if(frame.getNWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NW);
        if(frame.getSECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SE);
        if(frame.getNECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NE);
        if(cityQuadrants.isEmpty()){
            cityQuadrants.add(CityQuadrants.SW);
            cityQuadrants.add(CityQuadrants.SE);
            cityQuadrants.add(CityQuadrants.NW);
            cityQuadrants.add(CityQuadrants.NE);
        }


        SearchCriteria criteria = new SearchCriteria(propertyTypes, bedrooms, bathrooms, furnished, unfurnished, cityQuadrants);
        return criteria;
    }

    public class RegisterProperty implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            RegisterPropertyScreen currentScreen = view.getLandlordScreen().getRegPropertyScreen();
            Property p;
            try {
                PropertyType propertyType = PropertyType.valueOf(currentScreen.getPropertyTypeComboBox().getSelectedItem().toString());
                int bedrooms = 0;
                int bathrooms = 0;
                double rentalFee = Double.parseDouble(currentScreen.getCostTextField().getText());
                boolean furnished;
                Address address = new Address(currentScreen.getAddressTextField().getText(),currentScreen.getAddressTextField().getText(),
                        currentScreen.getAddressTextField().getText(), currentScreen.getAddressTextField().getText());
                CityQuadrants quadrant = CityQuadrants.valueOf(currentScreen.getCityQuadrantComboBox().getSelectedItem().toString());

                try {
                    bedrooms = Integer.parseInt(currentScreen.getBedroomTextField().getText());
                    bathrooms = Integer.parseInt(currentScreen.getBathroomTextField().getText());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (currentScreen.getFurnishedComboBox().getSelectedItem().toString().compareTo("Furnished") == 0)
                    furnished = true;
                else
                    furnished = false;

                p = new Property((Landlord) currentUser.get(), address, bedrooms, bathrooms, furnished, new Fee(rentalFee), propertyType);
            }catch (Exception e){
                view.getLandlordScreen().getRegPropertyScreen().getError().setVisible(true);
                return;
            }
            renterWebsite.propertyRepo.addProperty(p);
            renterWebsite.getMyDatabase().getPropertyDatabase().addProperty(p);

            currentScreen.setVisible(false);
        }
    }

    public class DisplayUnpaidFees implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            refreshUnpaidFees();
        }
    }

    public void refreshUnpaidFees()
    {
        ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
        displayProperties(view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList(), unpaidProperties);
    }

    public class DisplayLandlordProperties implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Property> properties = renterWebsite.propertyRepo.getLandlordProperties((Landlord)currentUser.get());
            displayProperties(view.getLandlordScreen().getPropertyList(), properties);
        }
    }

    public class PayUnpaidFees extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(e.getClickCount() == 2)
            {
                ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
                int index = view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().getSelectedIndex();
                view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().setVisible(true);
                view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().getTextArea1().setText(unpaidProperties.get(index).getRegistrationFee().toString());
            }
        }
    }

    public class PayFeeButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
            int index = view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().getSelectedIndex();

            unpaidProperties.get(index).getRegistrationFee().setPaid(true);
            refreshUnpaidFees();

            view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().setVisible(false);
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
}
