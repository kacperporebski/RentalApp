package Client.Repositories;

import Client.Container.Observer;
import Client.Container.Property;
import Client.Container.STATE;
import Client.Container.Subject;

import java.util.ArrayList;

/**
 * Singleton class which maintains list of properties
 */
public class PropertyRepository implements Subject
{
    private static ArrayList<Property> properties;
    private static PropertyRepository instance;
    private static ArrayList<Observer> observerList;

    private PropertyRepository()
    {
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
}
