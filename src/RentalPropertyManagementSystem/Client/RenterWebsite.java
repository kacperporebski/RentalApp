package Client;

import Client.Container.Account;
import Client.Container.Fee;
import Client.Container.Property;
import Client.Container.UserType;
import Client.Repositories.PropertyRepository;
import Client.Repositories.UserRepository;
import Users.Landlord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class RenterWebsite
{
   //TODO private Database myDatabase; ... Maybe add to Repo?

    public UserRepository userRepo;
    public PropertyRepository propertyRepo;

    public RenterWebsite()
    {
        userRepo = UserRepository.getInstance();
        propertyRepo = PropertyRepository.getInstance();
        serverReadInitialData();
    }

    //private ArrayList<Manager> managers;
    //private ArrayList<User> users;
    //private ArrayList<Property> properties;

    public void printAllData(){
        userRepo.printAll();
        propertyRepo.printAll();

    }

    private void serverReadInitialData() {

        try {
            FileReader fr1 = new FileReader("src\\landlords.txt");
            BufferedReader br1 = new BufferedReader(fr1);

            String line;
            while ((line = br1.readLine()) != null) {
                String[] temp = line.split(";");
                Landlord tempNewLandlord = new Landlord(temp[0], temp[1], temp[2], new Account(temp[3], temp[4], UserType.LANDLORD));
                userRepo.getInstance().addUser(tempNewLandlord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading in the list of landlords");
        }




        try {
            FileReader fr = new FileReader("src\\properties.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                //int idnum, Landlord l, String addr,int bedroom, int bathroom, boolean furnished, Fee rentfee
                Landlord tempMyL = userRepo.getInstance().findLandlord(temp[1], temp[2]);
                boolean furnished = false;
                if(temp[6] == "furnished")
                    furnished = true;
                Fee tempFee = new Fee(Double.parseDouble(temp[7]));

                propertyRepo.getInstance().addProperty(new Property(Integer.parseInt(temp[0]),tempMyL, temp[3],
                        Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), furnished, tempFee));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading in the list of properties");
        }
    }

    //TODO FUNCTIONS HELLO?



}
