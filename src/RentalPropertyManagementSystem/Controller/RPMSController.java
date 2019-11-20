package RentalPropertyManagementSystem.Controller;

import Client.RenterWebsite;
import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.GUI.SearchCriteriaScreen;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.Landlord;
import RentalPropertyManagementSystem.Users.Manager;
import RentalPropertyManagementSystem.Users.RegisteredRenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public RPMSController(GUI view, RenterWebsite website)
    {
        this.view = view;
        renterWebsite = website;

        //TODO add ActionListeners to view...
        view.getLoginScreen().getLoginButton().addActionListener(new LoginActionListener());
        view.getRegUserScreen().getRegisterButton().addActionListener(new RegisterUserActionListener());
        view.getRegRenterScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getRegRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());

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
                    case LANDLORD:
                        view.getLandlordScreen().setVisible(true);
                        break;
                    case MANAGER:
                        break;
                    case REGRENTER:
                        view.getRegRenterScreen().setVisible(true);

                        break;
                }

                view.getMenuScreen().setVisible(false);
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
                case MANAGER:
                    user = new Manager(firstname, lastname, email, account);
                    break;
                case LANDLORD:
                    user = new Landlord(firstname, lastname, email, account);
                    break;
                case REGRENTER:
                    user = new RegisteredRenter(firstname, lastname, email, account);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + account.getAccountType());
            }
            boolean added = renterWebsite.userRepo.addUser(user);
            if(added)
                System.out.println("Added user " + user.toString());
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


    public class ListPropertiesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == view.getRenterScreen().getRefreshButton())
                 displayProperties(view.getRenterScreen().getPropertyList());
            else if(e.getSource() == view.getRegRenterScreen().getRefreshButton())
                displayProperties(view.getRegRenterScreen().getPropertyList());
        }
    }

    public void displayProperties(JList list)
    {
        ArrayList<Property> propertyList = renterWebsite.propertyRepo.getAllProperties();
        list.setModel(renterWebsite.propertyRepo.toStringList(propertyList));
        System.out.println("Displaying properties");
    }

    public class SubscribeSearchCriteria implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            SearchCriteria criteria;
            if(e.getSource() == view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                criteria = createCriteria(view.getRenterScreen().getSearchCriteriaScreen());
                ((RegisteredRenter)currentUser.get()).setSearchCriteria(criteria);
            }
            else if(e.getSource() == view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                //Todo figure out how to convert renter into a registered renter
                view.getRegUserScreen().setVisible(true);
                view.getRegUserScreen().getAccountTypeBox().setSelectedIndex(3);
            }
        }
    }

    public SearchCriteria createCriteria(SearchCriteriaScreen frame)
    {
        ArrayList<PropertyType> propertyTypes = new ArrayList<>();
        ArrayList<Integer> bedrooms = new ArrayList<Integer>();
        ArrayList<Integer> bathrooms = new ArrayList<Integer>();
        boolean furnished;
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

        try
        {
            int lowerBedrooms = Integer.parseInt(frame.getBedroomLowerTextField().getText());
            int upperBedrooms = Integer.parseInt(frame.getBedroomUpperTextField().getText());
            int lowerBathrooms = Integer.parseInt(frame.getBathroomLowerTextField().getText());
            int upperBathrooms = Integer.parseInt(frame.getBathroomUpperTextField().getText());

            bedrooms.add(lowerBedrooms);
            bedrooms.add(upperBedrooms);
            bathrooms.add(lowerBathrooms);
            bathrooms.add(upperBathrooms);

        }catch(NumberFormatException e)
        {
            e.printStackTrace();
        }

        if(frame.getFurnishedCheckBox().isSelected())
            furnished = true;
        else
            furnished = false;

        if(frame.getSWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SW);
        if(frame.getNWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NW);
        if(frame.getSECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SE);
        if(frame.getNECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NE);


        SearchCriteria criteria = new SearchCriteria(propertyTypes, bedrooms, bathrooms, furnished, cityQuadrants);
        return criteria;
    }






}
