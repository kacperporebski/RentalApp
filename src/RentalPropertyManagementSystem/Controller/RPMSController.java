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
    ArrayList<Controller> controllers;

    public RPMSController(GUI view, RenterWebsite website)
    {
        ArrayList<Controller> controllers = new ArrayList<>();
        controllers.add(new LandlordController(view, website));
        controllers.add(new ManagerController(view, website));
        controllers.add(new RenterController(view, website));
        controllers.add(new LoginController(view, website));
    }
}