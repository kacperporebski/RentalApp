package Client.Controller;

import Client.Container.Account;
import Client.Container.Property;
import Client.Container.UserType;
import Client.GUI;
import Client.RenterWebsite;
import Users.AccountHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        //ie. view.getButton().addActionListener(new LoginActionListener());
    }

    public class LoginActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //TODO Get View Working
            //String username = view.getLoginUsernameField.getText();
            //String password = view.getLoginPasswordField.getText();

            String username = "";
            String password = "";

            AccountHolder user = renterWebsite.userRepo.login(username, password);
            /*if(account doesn't exist)
                view.displayInvalidLogin
              else
                switch case for which display to show depending on usertype
            */
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


    public class ListPropertiesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Property> propertyList = renterWebsite.propertyRepo.getAllProperties();

        }
    }





}
