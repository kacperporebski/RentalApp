package Client.Controller;

import Client.Container.Account;
import Client.GUI;
import Client.RenterWebsite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            Account account = renterWebsite.userRepo.login(username, password);
        }
    }





}
