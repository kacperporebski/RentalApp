package Users;

import Client.Container.*;

import java.util.ArrayList;

public class RegisteredRenter extends AccountHolder implements Observer
{
    Renter renter;
    ArrayList<Notification> notifications;

    public RegisteredRenter(String fname, String lname, String mail, Account account)
    {
        super(fname, lname, mail, account);
    }


    @Override
    public void update(ArrayList<Property> properties)
    {
        //Todo Currently this function recreates notifications for already added properties. Fix so that it only creates new ones for new properties.
        for(Property p : properties)
        {
            if(renter.searchCriteria.matchingProperty(p))
                createNotification(p, new Date());
        }
    }

    public void createNotification(Property property, Date date)
    {
        notifications.add(new Notification(property, date));
    }
}
