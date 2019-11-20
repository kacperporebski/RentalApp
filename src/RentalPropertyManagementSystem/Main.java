package RentalPropertyManagementSystem;

import Client.RenterWebsite;
import Database.Database;
import RentalPropertyManagementSystem.Controller.RPMSController;
import RentalPropertyManagementSystem.GUI.GUI;


public class Main
{
    public static void main(String args[])
    {
        GUI gui = new GUI();
        RenterWebsite model = new RenterWebsite();
        RPMSController controller = new RPMSController(gui, model);
        model.printAllData();
        Database d = new Database();
        d.getPropertyDatabase().createPropertyTable();
        d.getUserDatabase().createUserTable();
    }

}
