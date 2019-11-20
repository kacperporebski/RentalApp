package RentalPropertyManagementSystem;


import Database.Database;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.Controller.RPMSController;
import RentalPropertyManagementSystem.GUI.GUI;

import java.sql.SQLException;


public class Main
{
    public static void main(String args[]) throws SQLException {
        GUI gui = new GUI();
        RenterWebsite model = new RenterWebsite();
        RPMSController controller = new RPMSController(gui, model);
       // model.printAllData();
    }

}
