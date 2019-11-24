package RentalPropertyManagementSystem.Users;

import RentalPropertyManagementSystem.Client.Container.*;

import java.util.ArrayList;
import java.util.Iterator;

public class RegisteredRenter extends AccountHolder implements Observer
{
    Renter renter;
    ArrayList<Notification> notifications;
    ArrayList<Notification> clearedNotifications;

    public RegisteredRenter(String fname, String lname, String mail, Account account)
    {
        super(fname, lname, mail, account);
        renter = new Renter();
        notifications = new ArrayList<>();
        clearedNotifications = new ArrayList<>();
    }


    @Override
    public void update(ArrayList<Property> properties)
    {
        System.out.println("Updating registerRenter notifications\n");
        //Todo Currently this function recreates notifications for already added properties. Fix so that it only creates new ones for new properties.

        for(Property p : properties)
        {
            int check = 0;
            for (Notification n : notifications)
            {
                if (n.getNewProperty() == p)
                {
                    check = 1;
                    break;
                }
            }
            if(check != 1)
            {
                for (Notification n : clearedNotifications)
                {
                    if (n.getNewProperty() == p)
                    {
                        check = 1;
                        break;
                    }
                }
            }

            if (renter.searchCriteria.matchingProperty(p) && check == 0)
                notify(p, new Date());
        }
    }

    public void notify(Property property, Date date)
    {
        notifications.add(new Notification(property, date));
    }

    public void clearNotifications()
    {
        clearedNotifications.addAll(notifications);
        notifications.removeAll(notifications);
    }

    public void unsubscribe()
    {
        notifications = new ArrayList<>();
        clearedNotifications = new ArrayList<>();
    }

    public void removeNotification(Notification notification)
    {
        notifications.remove(notification);
    }

    public void setSearchCriteria(SearchCriteria criteria)
    {
        renter.searchCriteria = new SearchCriteria(criteria);
    }

    @Override
    public String toString()
    {
        return account.getAccountType().toString() + " " + super.toString();
    }

    public Renter getRenter()
    {
        return renter;
    }

    public ArrayList<Notification> getNotifications()
    {
        return notifications;
    }
}
