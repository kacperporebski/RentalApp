package RentalPropertyManagementSystem.Repositories;

import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Users.Landlord;

import javax.swing.*;
import java.lang.reflect.Array;
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
        System.out.println("Added property " + property.toString());
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

    public ArrayList<Property> getAllActiveProperties() {
        ArrayList<Property> props = new ArrayList<>();
        for(Property p : properties){
            if(p.getState() == STATE.ACTIVE)
                props.add(p);
        }
        return props;
    }

    public ArrayList<Property> getLandlordProperties(Landlord landlord)
    {
        ArrayList<Property> landlordProperties = new ArrayList<>();
        for(Property p: properties)
        {
            if (landlord == p.getMyLandlord())
                landlordProperties.add(p);
        }
        return landlordProperties;
    }

    public ArrayList<Property> getMatchingProperties(SearchCriteria criteria)
    {
        ArrayList<Property> matchingProperties = new ArrayList<>();
        for(Property p: properties)
        {
            if(criteria.matchingProperty(p) == true)
                matchingProperties.add(p);
        }
        return matchingProperties;
    }

    public ArrayList<Property> getLandlordUnpaidProperties(Landlord landlord)
    {
        ArrayList<Property> landlordProperties = getLandlordProperties(landlord);
        ArrayList<Property> unpaidProperties = new ArrayList<>();
        for(Property p : landlordProperties)
        {
            if(p.getRegistrationFee().isPaid() == false)
                unpaidProperties.add(p);
        }
        return unpaidProperties;
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

    public DefaultListModel<String> toStringList(ArrayList<Property> pList)
    {
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        for(Property p : pList)
        {
            dlm.addElement(p.toString());
        }
        return dlm;
    }

    public DefaultListModel<String> toStringSummaryList(ArrayList<Property> pList)
    {
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        for(Property p : pList)
        {
           // dlm.addElement(p.toStringSummary());
        }
        return dlm;
    }
}
