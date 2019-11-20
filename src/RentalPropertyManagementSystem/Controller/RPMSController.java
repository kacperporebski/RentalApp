package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.GUI.RegisterPropertyScreen;
import RentalPropertyManagementSystem.GUI.SearchCriteriaScreen;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.Landlord;
import RentalPropertyManagementSystem.Users.Manager;
import RentalPropertyManagementSystem.Users.RegisteredRenter;

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


    public RPMSController(GUI view, RenterWebsite website) {
        this.view = view;
        renterWebsite = website;

        //TODO add ActionListeners to view...
        view.getLoginScreen().getLoginButton().addActionListener(new LoginActionListener());

        view.getRegUserScreen().getRegisterButton().addActionListener(new RegisterUserActionListener());

        view.getRegRenterScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getRegRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());
        view.getRegRenterScreen().getSearchCriteriaScreen().getEnterButton().addActionListener(new EnterSearchCriteria());

        view.getRenterScreen().getSearchCriteriaScreen().getEnterButton().addActionListener(new EnterSearchCriteria());
        view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());
        view.getRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());

        view.getLandlordScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getLandlordScreen().getRegPropertyScreen().getRegisterPropertyButton().addActionListener(new RegisterProperty());
        view.getLandlordScreen().getUnpaidFeeScreen().getRefreshButton().addActionListener(new DisplayUnpaidFees());
        view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().addMouseListener(new PayUnpaidFees());

        view.getManagerScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getManagerScreen().getChangeFeeScreen().getChangeFeeButton().addActionListener(new ChangeFeeActionListener());
        view.getManagerScreen().getChangeRegistrationFeeButton().addActionListener(new ChangeRegFeeActionListener());
        view.getManagerScreen().getRequestSummaryReportButton().addActionListener(new RequestSummaryReportActionListener());
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
        public void actionPerformed(ActionEvent e)
        {
            String username = view.getRegUserScreen().getUsernameTextField().getText();
            String password = view.getRegUserScreen().getPasswordTextField().getText();
            UserType type = UserType.valueOf(view.getRegUserScreen().getAccountTypeBox().getSelectedItem().toString());
            Account account = new Account(username, password, type);
            String firstname = view.getRegUserScreen().getFirstNameTextField().getText();
            String lastname = view.getRegUserScreen().getLastNameTextField().getText();
            String email = view.getRegUserScreen().getEmailTextField().getText();

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
                view.getRegUserScreen().setVisible(false);
            }
            else
                System.out.println("User already exists " + user.toString());
            view.getLoginScreen().setVisible(true);
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
        }
    }

    public class ListPropertiesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == view.getRenterScreen().getRefreshButton())
                 displayProperties(view.getRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllProperties());
            else if(e.getSource() == view.getRegRenterScreen().getRefreshButton())
                displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllProperties());
        }
    }

    public void displayProperties(JList list, ArrayList<Property> propertyList)
    {
        list.setModel(renterWebsite.propertyRepo.toStringList(propertyList));
        System.out.println("Displaying properties");
    }

    public void displaySummaryProperties(JList list, ArrayList<Property> propertyList)
    {
        list.setModel(renterWebsite.propertyRepo.toStringSummaryList(propertyList));
        System.out.println("Displaying properties");
    }

    public class RequestSummaryReportActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int active = 0;
            int listed = 0;
            int rented = 0;
            for(Property p : renterWebsite.propertyRepo.getAllProperties()){
                if(p.getState() == STATE.ACTIVE)
                    active++;
                //if(p.getState() == STATE.)
            }

            ArrayList<Property> housesRented = new ArrayList<>();

            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();
            Period period = new Period(currentMonth, currentYear);

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
                criteria = createCriteria(view.getRenterScreen().getSearchCriteriaScreen());
                ((RegisteredRenter)currentUser.get()).setSearchCriteria(criteria);
                view.getRegRenterScreen().getSearchCriteriaScreen().setVisible(false);
            }
            //If Regular Renter:
            else if(e.getSource() == view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                //Todo figure out how to convert renter into a registered renter
                System.out.println("Must first register\n");
                view.getRegUserScreen().setVisible(true);
                view.getRegUserScreen().getAccountTypeBox().setSelectedIndex(3);
                view.getRenterScreen().getSearchCriteriaScreen().setVisible(false);
            }
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

        Integer lowerBedrooms = 0;
        Integer upperBedrooms = 0;
        Integer lowerBathrooms = 0;
        Integer upperBathrooms = 0;
        try
        {
             lowerBedrooms = Integer.parseInt(frame.getBedroomLowerTextField().getText());
             upperBedrooms = Integer.parseInt(frame.getBedroomUpperTextField().getText());
             lowerBathrooms = Integer.parseInt(frame.getBathroomLowerTextField().getText());
             upperBathrooms = Integer.parseInt(frame.getBathroomUpperTextField().getText());

        }catch(NumberFormatException e)
        {
            e.printStackTrace();
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

        if(frame.getSWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SW);
        if(frame.getNWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NW);
        if(frame.getSECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SE);
        if(frame.getNECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NE);


        SearchCriteria criteria = new SearchCriteria(propertyTypes, bedrooms, bathrooms, furnished, unfurnished, cityQuadrants);
        return criteria;
    }

    public class RegisterProperty implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            RegisterPropertyScreen currentScreen = view.getLandlordScreen().getRegPropertyScreen();
            PropertyType propertyType = PropertyType.valueOf(currentScreen.getPropertyTypeComboBox().getSelectedItem().toString());
            int bedrooms = 0;
            int bathrooms = 0;
            double rentalFee = Double.parseDouble(currentScreen.getCostTextField().getText());
            boolean furnished;
            String address = currentScreen.getAddressTextField().getText();
            CityQuadrants quadrant = CityQuadrants.valueOf(currentScreen.getCityQuadrantComboBox().getSelectedItem().toString());

            try{
                bedrooms = Integer.parseInt(currentScreen.getBedroomTextField().getText());
                bathrooms = Integer.parseInt(currentScreen.getBathroomTextField().getText());
            }catch(NumberFormatException e)
            {
                e.printStackTrace();
            }

            if(currentScreen.getFurnishedComboBox().getSelectedItem().toString().compareTo("Furnished") == 0)
                furnished = true;
            else
                furnished = false;

            renterWebsite.propertyRepo.addProperty(new Property((Landlord)currentUser.get(), address, bedrooms, bathrooms, furnished, new Fee(rentalFee), propertyType, quadrant));
        }
    }

    public class DisplayUnpaidFees implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
            displayProperties(view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList(), unpaidProperties);
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
        }
    }



}
