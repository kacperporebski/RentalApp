package RentalPropertyManagementSystem.Controller;

import Client.RenterWebsite;
import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Client.Container.Property;
import RentalPropertyManagementSystem.Client.Container.UserType;
import RentalPropertyManagementSystem.GUI.GUI;
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
        view.getRegRenterScreen().getLogoutButton().addActionListener(new LoginActionListener());
        view.getRegRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());

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
                System.out.println("Logged in");

                switch (user.get().getAccountType())
                {
                    case LANDLORD:
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
            
            displayProperties(view.getRenterScreen().getPropertyList());
        }
    }

    public void displayProperties(JList list)
    {
        ArrayList<Property> propertyList = renterWebsite.propertyRepo.getAllProperties();

        DefaultListModel<String> dlm = new DefaultListModel<String>();
        for(Property p : propertyList)
        {
            dlm.addElement(p.toString());
        }
        list.setModel(dlm);
        System.out.println("Displaying properties");
    }





}
