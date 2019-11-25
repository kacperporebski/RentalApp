package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.GUI.SearchCriteriaScreen;
import RentalPropertyManagementSystem.Users.RegisteredRenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RenterController extends Controller
{
    public RenterController(GUI view, RenterWebsite website)
    {
        super(view, website);

        view.getRegRenterScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getRegRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());
        view.getRegRenterScreen().getSearchCriteriaScreen().getEnterButton().addActionListener(new EnterSearchCriteria());
        view.getRegRenterScreen().getPropertyList().addMouseListener(new DoubleClickRentRentOrEmail());
        view.getRegRenterScreen().getDisplayNotificationsButton().addActionListener(new DisplayNotificationsActionListener());
        view.getRegRenterScreen().getNotificationScreen().getClearNotificationsButton().addActionListener(new ClearNotificationsActionListener());
        view.getRegRenterScreen().getNotificationScreen().getRefreshButton().addActionListener(new DisplayNotificationsActionListener());
        view.getRegRenterScreen().getNotificationScreen().getNotificationList().addMouseListener(new DoubleClickRentRentOrEmail());
        view.getRegRenterScreen().getNotificationScreen().getRemoveSelectedButton().addActionListener(new RemoveSelectedNotificationActionListener());
        view.getRegRenterScreen().getUnsubscribeButton().addActionListener(new UnsubscribeRegRenterActionListener());

        view.getRenterScreen().getSearchCriteriaScreen().getEnterButton().addActionListener(new EnterSearchCriteria());
        view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton().addActionListener(new SubscribeSearchCriteria());
        view.getRenterScreen().getRefreshButton().addActionListener(new ListPropertiesActionListener());
        view.getRenterScreen().getPropertyList().addMouseListener(new DoubleClickRentRentOrEmail());
    }

    public void displayNotifications(JList list, ArrayList<Notification> notificationList)
    {
        list.setModel(renterWebsite.userRepo.toStringNotificationList(notificationList));
        System.out.println("Displaying properties");
    }

    public class DoubleClickRentRentOrEmail extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if (e.getClickCount() == 2)
            {
                if (e.getSource() == view.getRegRenterScreen().getPropertyList())
                {
                    for (ActionListener t : view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().getActionListeners())
                    {
                        view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().removeActionListener(t);
                    }
                    int index = view.getRegRenterScreen().getPropertyList().getSelectedIndex();

                    view.getRegRenterScreen().getPayOrEmailScreen().getPropertyInfoTextArea().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).toString());
                    view.getRegRenterScreen().getPayFeeScreen().getTextArea1().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).getRent().toString());
                    view.getRegRenterScreen().getPayOrEmailScreen().setVisible(true);

                    view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new RegRenterPayProperty(index));
                    view.getRegRenterScreen().getSendEmailScreen().getSender().setText(currentUser.get().getEmail());
                } else if (e.getSource() == view.getRenterScreen().getPropertyList())
                {
                    for (ActionListener t : view.getRenterScreen().getPayFeeScreen().getPayFeeButton().getActionListeners())
                    {
                        view.getRenterScreen().getPayFeeScreen().getPayFeeButton().removeActionListener(t);
                    }
                    int index = view.getRenterScreen().getPropertyList().getSelectedIndex();

                    view.getRenterScreen().getPayOrEmailScreen().getPropertyInfoTextArea().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).toString());
                    view.getRenterScreen().getPayFeeScreen().getTextArea1().setText(renterWebsite.propertyRepo.getAllActiveProperties().get(index).getRent().toString());
                    view.getRenterScreen().getPayOrEmailScreen().setVisible(true);

                    view.getRenterScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new RegRenterPayProperty(index));
                } else if (e.getSource() == view.getRegRenterScreen().getNotificationScreen().getNotificationList())
                {
                    for (ActionListener t : view.getRenterScreen().getPayFeeScreen().getPayFeeButton().getActionListeners())
                    {
                        view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().removeActionListener(t);
                    }
                    int index = view.getRegRenterScreen().getNotificationScreen().getNotificationList().getSelectedIndex();
                    view.getRegRenterScreen().getPayOrEmailScreen().getPropertyInfoTextArea().setText(((RegisteredRenter)currentUser.get()).getNotifications().get(index).getNewProperty().toString());

                    view.getRegRenterScreen().getPayFeeScreen().getTextArea1().setText(((RegisteredRenter)currentUser.get()).getNotifications().get(index).getNewProperty().getRent().toString());
                    view.getRegRenterScreen().getPayOrEmailScreen().setVisible(true);
                    view.getRegRenterScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new RegRenterPayProperty(index));
                }
            }
        }
    }

    public class RegRenterPayProperty implements ActionListener
    {
        int index;
        public RegRenterPayProperty(int index){this.index = index;}
        @Override
        public void actionPerformed(ActionEvent e)
        {
            renterWebsite.propertyRepo.getAllActiveProperties().get(index).getRent().isPaid();
            renterWebsite.propertyRepo.getAllActiveProperties().get(index).setState(STATE.RENTED);
            //renterWebsite.propertyRepo.getAllActiveProperties().get(index).setDateRented(new Date());
            displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
            ArrayList<Notification> notificationList = ((RegisteredRenter)currentUser.get()).getNotifications();
            displayNotifications(view.getRegRenterScreen().getNotificationScreen().getNotificationList(), notificationList);

            view.getRegRenterScreen().getPayFeeScreen().setVisible(false);
            view.getRegRenterScreen().getPayOrEmailScreen().setVisible(false);
        }
    }

    public class ListPropertiesActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == view.getRenterScreen().getRefreshButton())
                displayProperties(view.getRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
            else if(e.getSource() == view.getRegRenterScreen().getRefreshButton())
                displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getAllActiveProperties());
        }
    }

    public class UnsubscribeRegRenterActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ((RegisteredRenter)(currentUser.get())).unsubscribe();
            ArrayList<Notification> notificationList = ((RegisteredRenter)currentUser.get()).getNotifications();
            displayNotifications(view.getRegRenterScreen().getNotificationScreen().getNotificationList(), notificationList);
            renterWebsite.propertyRepo.removeObserver(((RegisteredRenter)currentUser.get()));
        }
    }

    public class EnterSearchCriteria implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            SearchCriteria criteria;
            if(e.getSource() == view.getRegRenterScreen().getSearchCriteriaScreen().getEnterButton())
            {
                criteria = createCriteria(view.getRegRenterScreen().getSearchCriteriaScreen());
                displayProperties(view.getRegRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getMatchingProperties(criteria));
                view.getRegRenterScreen().getSearchCriteriaScreen().setVisible(false);
            }
            //If Regular Renter:
            else if(e.getSource() == view.getRenterScreen().getSearchCriteriaScreen().getEnterButton())
            {
                //Todo figure out how to convert renter into a registered renter
                criteria = createCriteria(view.getRenterScreen().getSearchCriteriaScreen());
                displayProperties(view.getRenterScreen().getPropertyList(), renterWebsite.propertyRepo.getMatchingProperties(criteria));
                view.getRenterScreen().getSearchCriteriaScreen().setVisible(false);
            }

        }
    }

    public class SubscribeSearchCriteria implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            SearchCriteria criteria;
            //If Registered Renter:
            if(e.getSource() == view.getRegRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                criteria = createCriteria(view.getRegRenterScreen().getSearchCriteriaScreen());

                System.out.printf("Search Criteria " + criteria.toString());
                ((RegisteredRenter)currentUser.get()).setSearchCriteria(criteria);
                view.getRegRenterScreen().getSearchCriteriaScreen().setVisible(false);
                renterWebsite.propertyRepo.registerObserver(((RegisteredRenter)currentUser.get()));

                if(renterWebsite.getMyDatabase().getSearchCriteriaDatabase().existSearchCritera((RegisteredRenter)currentUser.get())){
                    renterWebsite.getMyDatabase().getSearchCriteriaDatabase().removeSearchCritera((RegisteredRenter)currentUser.get());
                }

                renterWebsite.getMyDatabase().getSearchCriteriaDatabase().addSearchCriteria((RegisteredRenter)currentUser.get());
            }
            //If Regular Renter:
            else if(e.getSource() == view.getRenterScreen().getSearchCriteriaScreen().getSubscribeButton())
            {
                //Todo figure out how to convert renter into a registered renter

                criteria = new SearchCriteria(createCriteria(view.getRenterScreen().getSearchCriteriaScreen()));
                renter.setSearchCriteria(criteria);

                System.out.println("Must first register\n");
                view.getRegUserScreen().setVisible(true);
                view.getRegUserScreen().getAccountTypeBox().setSelectedIndex(2);
                view.getRenterScreen().getSearchCriteriaScreen().setVisible(false);
                view.getRenterScreen().setVisible(false);
            }
        }
    }

    public class DisplayNotificationsActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Notification> notificationList = ((RegisteredRenter)currentUser.get()).getNotifications();
            displayNotifications(view.getRegRenterScreen().getNotificationScreen().getNotificationList(), notificationList);
            view.getRegRenterScreen().getNotificationScreen().setVisible(true);
        }
    }

    public class ClearNotificationsActionListener implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ((RegisteredRenter)currentUser.get()).clearNotifications();
            ArrayList<Notification> notificationList = ((RegisteredRenter)currentUser.get()).getNotifications();
            displayNotifications(view.getRegRenterScreen().getNotificationScreen().getNotificationList(), notificationList);
        }
    }

    public class RemoveSelectedNotificationActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int index = view.getRegRenterScreen().getNotificationScreen().getNotificationList().getSelectedIndex();
            ((RegisteredRenter)currentUser.get()).removeNotification(index);
            ArrayList<Notification> notificationList = ((RegisteredRenter)currentUser.get()).getNotifications();
            displayNotifications(view.getRegRenterScreen().getNotificationScreen().getNotificationList(), notificationList);
        }
    }

    public SearchCriteria createCriteria(SearchCriteriaScreen frame)
    {
        ArrayList<PropertyType> propertyTypes = new ArrayList<>();
        ArrayList<Integer> bedrooms = new ArrayList<>();
        ArrayList<Integer> bathrooms = new ArrayList<>();
        boolean furnished;
        boolean unfurnished;
        ArrayList<CityQuadrants> cityQuadrants = new ArrayList<>();

        if(frame.getApartmentCheckBox().isSelected())
            propertyTypes.add(PropertyType.Apartment);
        if(frame.getAttachedHouseCheckBox().isSelected())
            propertyTypes.add(PropertyType.AttachedHouse);
        if(frame.getDetachedHouseCheckBox().isSelected())
            propertyTypes.add(PropertyType.DetachedHouse);
        if(frame.getTownHouseCheckBox().isSelected())
            propertyTypes.add(PropertyType.TownHouse);
        if(frame.getCondoCheckBox().isSelected())
            propertyTypes.add(PropertyType.Condo);
        if(frame.getDuplexCheckBox().isSelected())
            propertyTypes.add(PropertyType.Duplex);
        if(propertyTypes.isEmpty()){
            propertyTypes.add(PropertyType.Apartment);
            propertyTypes.add(PropertyType.AttachedHouse);
            propertyTypes.add(PropertyType.DetachedHouse);
            propertyTypes.add(PropertyType.TownHouse);
            propertyTypes.add(PropertyType.Condo);
            propertyTypes.add(PropertyType.Duplex);
        }

        Integer lowerBedrooms;
        Integer upperBedrooms;
        Integer lowerBathrooms;
        Integer upperBathrooms;
        try {
            lowerBedrooms = Integer.parseInt(frame.getBedroomLowerTextField().getText());
        } catch(NumberFormatException e) {
            lowerBedrooms = 0;
        }
        try{
            upperBedrooms = Integer.parseInt(frame.getBedroomUpperTextField().getText());
        } catch(NumberFormatException e){
            upperBedrooms = 100;
        }

        try{
            lowerBathrooms = Integer.parseInt(frame.getBathroomLowerTextField().getText());
        } catch(NumberFormatException e){
            lowerBathrooms = 0;
        }

        try{
            upperBathrooms = Integer.parseInt(frame.getBathroomUpperTextField().getText());
        } catch(NumberFormatException e){
            upperBathrooms = 100;
        }

        bedrooms.add(lowerBedrooms);
        bedrooms.add(upperBedrooms);
        bathrooms.add(lowerBathrooms);
        bathrooms.add(upperBathrooms);

        if(frame.getFurnishedCheckBox().isSelected())
            furnished = true;
        else
            furnished = false;

        if(frame.getUnfurnishedCheckBox().isSelected())
            unfurnished = true;
        else
            unfurnished = false;

        if(!unfurnished && !furnished) {
            furnished = true;
            unfurnished = true;
        }

        if(frame.getSWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SW);
        if(frame.getNWCheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NW);
        if(frame.getSECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.SE);
        if(frame.getNECheckBox().isSelected())
            cityQuadrants.add(CityQuadrants.NE);
        if(cityQuadrants.isEmpty()){
            cityQuadrants.add(CityQuadrants.SW);
            cityQuadrants.add(CityQuadrants.SE);
            cityQuadrants.add(CityQuadrants.NW);
            cityQuadrants.add(CityQuadrants.NE);
        }


        SearchCriteria criteria = new SearchCriteria(propertyTypes, bedrooms, bathrooms, furnished, unfurnished, cityQuadrants);
        return criteria;
    }

}
