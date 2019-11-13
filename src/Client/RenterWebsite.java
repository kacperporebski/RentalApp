package Client;

import Client.Container.Property;
import Client.Repositories.PropertyRepository;
import Client.Repositories.UserRepository;

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
    }

    //private ArrayList<Manager> managers;
    //private ArrayList<User> users;
    //private ArrayList<Property> properties;


    //TODO FUNCTIONS HELLO?



}
