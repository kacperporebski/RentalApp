package RentalPropertyManagementSystem.Controller;

import Client.RenterWebsite;
import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Client.Container.Property;
import RentalPropertyManagementSystem.Client.Container.UserType;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.Users.AccountHolder;

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

    public RPMSController(GUI view, RenterWebsite website)
    {
        this.view = view;
        renterWebsite = website;

        //TODO add ActionListeners to view...
        view.getLoginScreen().getLoginButton().addActionListener(new LoginActionListener());

    }

    public class LoginActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String username = view.getLoginScreen().getUsernameTextField().getText();
            String password = view.getLoginScreen().getPasswordField1().getPassword().toString();

            Optional<AccountHolder> user = renterWebsite.userRepo.login(username, password);
            //TODO switch case for which display to show depending on usertype
            if(user.isPresent())
            {
                switch (user.get().getAccountType())
                {
                    case LANDLORD:
                        break;
                    case MANAGER:
                        break;
                    case REGRENTER:
                        break;
                }
            }
            else
            {
                System.out.println("Couldn't login");

            }
        }
    }

    public class RegisterUserActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String username = "";
            String password = "";
            UserType type = UserType.LANDLORD;
            Account account = new Account(username, password, type);

            renterWebsite.userRepo.addUser(account);

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
            ArrayList<Property> propertyList = renterWebsite.propertyRepo.getAllProperties();

        }
    }





}
