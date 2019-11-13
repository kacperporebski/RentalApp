import Client.Controller.RPMSController;
import Client.GUI.GUI;
import Client.RenterWebsite;

public class Main
{
    public static void main(String args[])
    {
        GUI gui = new GUI();
        RenterWebsite model = new RenterWebsite();
        RPMSController controller = new RPMSController(gui, model);
    }

}
