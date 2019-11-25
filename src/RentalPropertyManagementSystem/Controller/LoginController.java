package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Client.Container.UserType;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.Landlord;
import RentalPropertyManagementSystem.Users.RegisteredRenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginController extends Controller
{
    public LoginController(GUI view, RenterWebsite website)
    {
        super(view, website);

        view.getLoginScreen().getLoginButton().addActionListener(new LoginActionListener());
        view.getRegUserScreen().getRegisterButton().addActionListener(new RegisterUserActionListener());
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
                        displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
                        break;
                }

                view.getMenuScreen().setVisible(false);
                view.getLoginScreen().setVisible(false);
                view.getLoginScreen().getPasswordTextField().setText("");
                view.getLoginScreen().getUsernameTextField().setText("");
            }
            else
            {
                System.out.println("Couldn't login");
                view.getLoginScreen().getError().setVisible(true);
            }
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
            else {
                System.out.println("User already exists " + user.toString());
                view.getRegUserScreen().getUserExists().setVisible(true);
            }

            renter.setSearchCriteria(null);
        }
    }

}
