package RentalPropertyManagementSystem.Client;


import Database.Database;
import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Repositories.PropertyRepository;
import RentalPropertyManagementSystem.Repositories.UserRepository;
import RentalPropertyManagementSystem.Users.Landlord;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class RenterWebsite
{
     private Database myDatabase;

    public UserRepository userRepo;
    public PropertyRepository propertyRepo;

    public RenterWebsite() throws SQLException {
        myDatabase = new Database();
        userRepo = UserRepository.getInstance();
        propertyRepo = PropertyRepository.getInstance();
        serverReadUserData();
        serverReadPropertyData();
    }

    //private ArrayList<Manager> managers;
    //private ArrayList<User> users;
    //private ArrayList<Property> properties;

    public void printAllData()
    {
        userRepo.printAll();
        propertyRepo.printAll();

    }

   /* private void serverReadInitialData() throws SQLException {
        if (myDatabase.getUserDatabase().tableCreated() == true){
        //TODO READ FROM DATABASE AAAA
                myDatabase.getUserDatabase().readIntoRepo(userRepo);
                myDatabase.getPropertyDatabase().readIntoRepo(propertyRepo, userRepo);

    }
        else {
            //IF TABLES NOT CREATED MAKE DATABASE FROM .TXT FILES
            myDatabase.getUserDatabase().createUserTable();
            myDatabase.getPropertyDatabase().createPropertyTable();
            try {
                FileReader fr1 = new FileReader("src\\landlords.txt");
                BufferedReader br1 = new BufferedReader(fr1);

                String line;
                while ((line = br1.readLine()) != null) {
                    String[] temp = line.split(";");
                    Landlord tempNewLandlord = new Landlord(temp[0], temp[1], temp[2], new Account(temp[3], temp[4], UserType.LANDLORD));
                   // userRepo.getInstance().addUser(tempNewLandlord);
                    myDatabase.getUserDatabase().addUser(tempNewLandlord);
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
                    System.out.println(tempMyL.getUsername());
                    boolean furnished = false;
                    if (temp[6] == "furnished")
                        furnished = true;
                    Fee tempFee = new Fee(Double.parseDouble(temp[7]));
                    Property p = new Property(tempMyL, temp[3],
                            Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), furnished, tempFee, PropertyType.valueOf(temp[0]));
                    myDatabase.getPropertyDatabase().addProperty(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error reading in the list of properties");
            }
            serverReadInitialData();
        }
    }

*/
    public void serverReadUserData() throws SQLException {
        if (myDatabase.getUserDatabase().tableCreated() == true) {
            myDatabase.getUserDatabase().readIntoRepo(userRepo);
        } else {
            //IF TABLES NOT CREATED MAKE DATABASE FROM .TXT FILES
            myDatabase.getUserDatabase().createUserTable();
            try {
                FileReader fr1 = new FileReader("src\\landlords.txt");
                BufferedReader br1 = new BufferedReader(fr1);

                String line;
                while ((line = br1.readLine()) != null) {
                    String[] temp = line.split(";");
                    Landlord tempNewLandlord = new Landlord(temp[0], temp[1], temp[2], new Account(temp[3], temp[4], UserType.LANDLORD));
                    // userRepo.getInstance().addUser(tempNewLandlord);
                    myDatabase.getUserDatabase().addUser(tempNewLandlord);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error reading in the list of landlords");
            }

            serverReadUserData();
        }
    }


        public void serverReadPropertyData() throws SQLException {

            if (myDatabase.getPropertyDatabase().tableCreated() == true){
                myDatabase.getPropertyDatabase().readIntoRepo(propertyRepo, userRepo);
            }
            else {
                myDatabase.getPropertyDatabase().createPropertyTable();
                try {
                    FileReader fr = new FileReader("src\\properties.txt");
                    BufferedReader br = new BufferedReader(fr);

                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] temp = line.split(";");
                        //int idnum, Landlord l, String addr,int bedroom, int bathroom, boolean furnished, Fee rentfee
                        Landlord tempMyL = userRepo.getInstance().findLandlord(temp[1], temp[2]);
                        System.out.println(tempMyL.getUsername());
                        boolean furnished = false;
                        if (temp[6] == "furnished")
                            furnished = true;
                        Fee tempFee = new Fee(Double.parseDouble(temp[7]));
                        Property p = new Property(tempMyL, temp[3],
                                Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), furnished, tempFee, PropertyType.valueOf(temp[0]));
                        myDatabase.getPropertyDatabase().addProperty(p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error reading in the list of properties");
                }
                serverReadPropertyData();
            }


        }



    //TODO FUNCTIONS HELLO?


}
