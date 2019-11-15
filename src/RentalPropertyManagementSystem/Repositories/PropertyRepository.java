package RentalPropertyManagementSystem.Repositories;

import RentalPropertyManagementSystem.Client.Container.Observer;
import RentalPropertyManagementSystem.Client.Container.Property;
import RentalPropertyManagementSystem.Client.Container.STATE;
import RentalPropertyManagementSystem.Client.Container.Subject;

import java.util.ArrayList;

/**
 * Singleton class which maintains list of properties
 */
public class PropertyRepository implements Subject
{
    private static ArrayList<Property> properties = new ArrayList<>();
    private static PropertyRepository instance;
    private static ArrayList<Observer> observerList = new ArrayList<>();

    private PropertyRepository()
    {
        properties= new ArrayList<Property>();
        observerList = new ArrayList<Observer>();
    }

    public void addProperty(Property property)
    {
        properties.add(property);
        notifyObserver();
    }

    public void removeProperty(Property property)
    {
        notifyObserver();
        properties.remove(property);
    }

    public void updatePropertyState(Property property, STATE state)
    {
        for(Property p : properties)
        {
            if(p.equals(property))
            {
                p.setState(state);
                break;
            }
        }
    }

    public void updateProperty(Property oldProperty, Property newProperty)
    {
        for(Property p : properties)
        {
            if(p.equals(oldProperty))
            {
                properties.set(properties.indexOf(p), newProperty);
                break;
            }
        }
    }

    public ArrayList<Property> getAllProperties()
    {
        return properties;
    }


    @Override
    public void registerObserver(Observer obs)
    {
        observerList.add(obs);
    }

    @Override
    public void removeObserver(Observer obs)
    {
        observerList.remove(obs);
    }

    @Override
    public void notifyObserver()
    {
        for(Observer o : observerList)
        {
            o.update(properties);
        }
    }

    public static PropertyRepository getInstance()
    {
        if(instance == null)
            instance = new PropertyRepository();
        return instance;
    }


    public void printAll(){
        for(Property x : properties){
            System.out.println(x.getAddress() + " Num of bedrooms and bathrooms : " +
                    x.getNumberOfBedrooms() + " , " + x.getNumberOfBathrooms() +
                    "\nLandlord info: " + x.getMyLandlord().toString());
        }

    }
}
