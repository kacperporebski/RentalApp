package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Client.Container.Property;
import RentalPropertyManagementSystem.Client.Container.UserType;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.Landlord;
import RentalPropertyManagementSystem.Users.RegisteredRenter;
import RentalPropertyManagementSystem.Users.Renter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Controller
{
    GUI view;
    RenterWebsite renterWebsite;
    static Optional<AccountHolder> currentUser;
    static Renter renter;

    public Controller(GUI view, RenterWebsite website)
    {
        this.view = view;
        this.renterWebsite = website;
    }

    public void displayProperties(JList list, ArrayList<Property> propertyList)
    {
        list.setModel(renterWebsite.propertyRepo.toStringList(propertyList));
        System.out.println("Displaying properties");
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
}