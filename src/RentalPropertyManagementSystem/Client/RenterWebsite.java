package RentalPropertyManagementSystem.Client;

import RentalPropertyManagementSystem.Repositories.PropertyRepository;
import RentalPropertyManagementSystem.Repositories.UserRepository;

public class RenterWebsite
{
   //TODO private RentalPropertyManagementSystem.Database myDatabase; ... Maybe add to Repo?

    public UserRepository userRepo;
    public PropertyRepository propertyRepo;

    public RenterWebsite()
    {
        userRepo = UserRepository.getInstance();
        propertyRepo = PropertyRepository.getInstance();
    }

    //private ArrayList<Manager> managers;
    //private ArrayList<User> users;
    //private ArrayList<Property> properties;


    //TODO FUNCTIONS HELLO?



}
